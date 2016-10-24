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
	get() = if (this < 0) (-this).toByte() else this

internal fun ByteArray.clear() {
	for (i in indices) this[i] = 0
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

		MAIN_THREAD.post { answer(scaled) }
	}
}

private fun downSample(data: ByteArray, targetSize: Int): ByteArray {
	val targetSized = ByteArray(targetSize, { 0 })
	val reducedSampleSize = (data.size / targetSize * 1.2F).toInt()
	val reducedSample = ByteArray(reducedSampleSize)

	var sampleIndex = 0
	var prevDataIndex = 0

	if (targetSize >= data.size) return targetSized.paste(data)

	data.forEachIndexed { i, byte ->
		val currentDataIndex = targetSized.size * i / data.size

		if (prevDataIndex == currentDataIndex) {
			reducedSample[sampleIndex] = byte.abs
		} else {
			sampleIndex = 0
			targetSized[currentDataIndex - 1] = reducedSample.average().toByte()
			reducedSample.clear()
		}

		prevDataIndex = currentDataIndex
		sampleIndex += 1
	}

	targetSized[prevDataIndex] = reducedSample.average().toByte()
	reducedSample.clear()

	return targetSized
}