package rm.com.audiogram

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import java.io.BufferedOutputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream

/**
 * Created by alex
 */
object AudioRecorder {

	private var path: File? = null
	private var recording: Boolean = false

	fun startAudioRecord(fileName: String) {
		inMediaWorker {
			path = File("${extDir.absolutePath}/$fileName").apply { createNewFile() }
			recording = true

			val outputStream = FileOutputStream(path)
			val bufferedOutputStream = BufferedOutputStream(outputStream)
			val dataOutputStream = DataOutputStream(bufferedOutputStream)

			val minBufferSize = AudioRecord.getMinBufferSize(44100,
					AudioFormat.CHANNEL_IN_MONO,
					AudioFormat.ENCODING_PCM_16BIT)

			val audioData = ByteArray(minBufferSize)

			val audioRecord = AudioRecord(MediaRecorder.AudioSource.MIC,
					44100,
					AudioFormat.CHANNEL_IN_MONO,
					AudioFormat.ENCODING_PCM_16BIT,
					minBufferSize)

			audioRecord.startRecording()

			while (recording) {
				val numberOfBytes = audioRecord.read(audioData, 0, minBufferSize)
				for (i in 0..numberOfBytes - 1) {
					dataOutputStream.writeByte(audioData[i].toInt())
				}
			}

			audioRecord.stop()
			dataOutputStream.close()
		}
	}

	fun stopAudioRecord(recorded: (ByteArray) -> Unit) {
		recording = false

		inMediaWorker {
			val contents = path?.readBytes() ?: byteArrayOf()

			inUI { recorded(contents) }
		}
	}
}