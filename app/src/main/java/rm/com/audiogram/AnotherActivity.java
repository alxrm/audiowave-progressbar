package rm.com.audiogram;

import android.app.Activity;
import android.os.Bundle;

import rm.com.audiowave.AudioWaveView;

public class AnotherActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_another);

    final AudioWaveView waveView = (AudioWaveView) findViewById(R.id.wave);
    final byte[] data = {1, 3, 37, 117, 69, 0, 0, 58};

    waveView.setScaledData(data);

//    waveView.setRawData(data, new Function0<Unit>() {
//      @Override
//      public Unit invoke() {
//        Log.d("Set raw data", "Callback called");
//        return null;
//      }
//    });
  }
}
