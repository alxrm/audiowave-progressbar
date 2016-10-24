package rm.com.audiogram

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.view.View
import rm.com.audiowave.AudioWaveView

class MainActivity : AppCompatActivity() {

	val wave by lazy { findViewById(R.id.wave) as AudioWaveView }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		Handler(Looper.getMainLooper()).postDelayed({
			wave.visibility = View.VISIBLE
			wave.unscaledData = assets.open("explosion.wav").readBytes()
		}, 1000)
	}
}