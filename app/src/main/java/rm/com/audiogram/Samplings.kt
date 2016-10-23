package rm.com.audiogram

import android.os.Handler
import android.os.Looper
import android.os.Process
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by alex
 */
val MAIN_THREAD = Handler(Looper.getMainLooper())
val SAMPLER_THREAD: ExecutorService = Executors.newSingleThreadExecutor { r ->
	object : Thread(r) {
		override fun run() {
			Process.setThreadPriority(Process.THREAD_PRIORITY_DEFAULT + Process.THREAD_PRIORITY_LESS_FAVORABLE)
			super.run()
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

	if (targetSize >= data.size) {
		return targetSized.apply {
			data.forEachIndexed { i, byte -> targetSized[i] = byte.abs }
		}
	}

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

val Byte.abs: Byte
	get() = if (this < 0) (-this).toByte() else this

fun ByteArray.clear() {
	for (i in indices) this[i] = 0
}