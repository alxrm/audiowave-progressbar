package rm.com.audiowave

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by alex
 */
internal val MAIN_THREAD = Handler(Looper.getMainLooper())
internal val SAMPLER_THREAD: ExecutorService = Executors.newSingleThreadExecutor()

internal val Byte.abs: Byte
	get() = when (this) {
		Byte.MIN_VALUE -> Byte.MAX_VALUE
		in (Byte.MIN_VALUE + 1..0) -> (-this).toByte()
		else -> this
	}

internal fun ByteArray.paste(other: ByteArray): ByteArray {
	if (size == 0) return byteArrayOf()

	return this.apply {
		forEachIndexed { i, byte ->
			this[i] = other.getOrElse(i, { this[i].abs })
		}
	}
}

internal fun downSampleAsync(data: ByteArray, targetSize: Int, answer: (ByteArray) -> Unit) {
	SAMPLER_THREAD.submit {
		val scaled = downSample(data, targetSize)

		MAIN_THREAD.post {
			answer(scaled)
		}
	}
}

internal fun downSample(data: ByteArray, targetSize: Int): ByteArray {
	val targetSized = ByteArray(targetSize, { 0 })
	val reducedSample = mutableListOf<Byte>()

	var prevDataIndex = 0

	if (targetSize >= data.size) {
		return targetSized.paste(data)
	}

	data.forEachIndexed { i, byte ->
		val currentDataIndex = targetSize * i / data.size

		if (prevDataIndex == currentDataIndex) {
			reducedSample += byte.abs
		} else {
			targetSized[currentDataIndex - 1] = reducedSample.average().toByte()
			reducedSample.clear()
		}

		prevDataIndex = currentDataIndex
	}

	targetSized[prevDataIndex] = reducedSample.average().toByte()

	return targetSized
}