package rm.com.audiogram;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.Random;
import rm.com.audiowave.AudioWaveView;
import rm.com.audiowave.OnProgressListener;
import rm.com.audiowave.OnSamplingListener;

public final class AnotherActivity extends Activity {

  @BindView(R.id.wave) AudioWaveView waveView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_another);
    ButterKnife.bind(this);

    final Random random = new Random();
    final byte[] data = new byte[1024 * 200];
    random.nextBytes(data);

    // if the data is downsampled already use setScaledData
    // waveView.setScaledData(data);

    // if the data is a raw big byte array, you can use setRawData
    waveView.setRawData(data, new OnSamplingListener() {
      @Override public void onComplete() {
        Log.e("DBG", "Finished downsampling");
      }
    });

    waveView.setOnProgressListener(new OnProgressListener() {
      @Override public void onStartTracking(float progress) {
        Log.e("wave", "Started tracking from: " + progress);
      }

      @Override public void onStopTracking(float progress) {
        Log.e("wave", "Stopped tracking at: " + progress);
      }

      @Override public void onProgressChanged(float progress, boolean byUser) {
        Log.e("wave", "Progress set: " + progress + ", and it's " + byUser + "that user did this");
      }
    });
  }
}
