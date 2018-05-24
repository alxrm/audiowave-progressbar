package rm.com.audiogram

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.Button
import rm.com.audiowave.AudioWaveView

class MainActivity : AppCompatActivity() {

  private val wave by lazy { findViewById<AudioWaveView>(R.id.wave) }
  private val play by lazy { findViewById<Button>(R.id.play) }
  private val list by lazy { findViewById<Button>(R.id.list_java) }
  private val simple by lazy { findViewById<Button>(R.id.simple_java) }

  private val progressAnim: ObjectAnimator by lazy {
    ObjectAnimator.ofFloat(wave, "progress", 0F, 100F).apply {
      interpolator = LinearInterpolator()
      duration = 1000
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    play.setOnClickListener {
      inflateWave()
    }

    list.setOnClickListener {
      startActivity(Intent(this, RecordListActivity::class.java))
    }

    simple.setOnClickListener {
      startActivity(Intent(this, AnotherActivity::class.java))
    }

    wave.onProgressChanged = { progress, byUser ->
      Log.e("wave", "Progress set: $progress, and it's $byUser that user did this")

      if (progress == 100F && !byUser) {
        wave.waveColor = ContextCompat.getColor(this, R.color.colorAccent)
        wave.isTouchable = true
      }
    }

    wave.onStartTracking = {
      Log.e("wave", "Started tracking from: $it")
    }

    wave.onStopTracking = {
      Log.e("wave", "Progress set: $it")
    }

//    wave.onProgressListener = object : OnProgressListener {
//      override fun onProgressChanged(progress: Float, byUser: Boolean) {
//        Log.e("wave", "Progress changed: $progress, and it's $byUser that user did this")
//      }
//
//      override fun onStartTracking(progress: Float) {
//        Log.e("wave", "Started tracking from: $progress")
//      }
//
//      override fun onStopTracking(progress: Float) {
//        Log.e("wave", "Stopped tracking at: $progress")
//      }
//    }
  }

  private fun inflateWave() {
    wave.setRawData(assets.open("sample.wav").readBytes()) { progressAnim.start() }
  }
}