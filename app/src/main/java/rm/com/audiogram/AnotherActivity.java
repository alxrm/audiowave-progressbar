package rm.com.audiogram;

import android.app.Activity;
import android.os.Bundle;
import butterknife.BindView;
import rm.com.audiowave.AudioWaveView;
import rm.com.audiowave.OnProgressListener;
import rm.com.audiowave.OnSamplingListener;

public final class AnotherActivity extends Activity {

  @BindView(R.id.wave) AudioWaveView waveView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_another);

    final byte[] data = {1, 3, 37, 117, 69, 0, 0, 58};

    waveView.setScaledData(data);
    waveView.setRawData(data, new OnSamplingListener() {
      @Override
      public void onComplete() {

      }
    });

    waveView.setOnProgressListener(new OnProgressListener() {
      @Override
      public void onStartTracking(float progress) {

      }

      @Override
      public void onStopTracking(float progress) {

      }

      @Override
      public void onProgressChanged(float progress, boolean byUser) {

      }
    });
  }
}
