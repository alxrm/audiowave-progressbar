package rm.com.audiogram

import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

	val wave by lazy { findViewById(R.id.wave) as AudioWaveView }
	val starter by lazy { findViewById(R.id.record) as Button }
	val stop by lazy { findViewById(R.id.stop) as Button }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		reqPermissionsOr(WRITE_EXTERNAL_STORAGE, RECORD_AUDIO) { onGranted() }
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)

		when (verifyPermissions(grantResults)) {
			PermissionState.GRANTED -> onGranted()
			PermissionState.DENIED -> onDeny()
		}
	}

	private fun onGranted() {
		starter.setOnClickListener {
			AudioRecorder.startAudioRecord("recording_of_tests.pcm")
		}

		stop.setOnClickListener {
			AudioRecorder.stopAudioRecord {
				onCompleteRecording(it)
			}
		}
	}

	private fun onCompleteRecording(res: ByteArray) {
		wave.visibility = View.VISIBLE
		wave.unscaledData = res
	}

	private fun onDeny() {
		finish()
	}
}