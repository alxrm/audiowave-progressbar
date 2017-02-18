package rm.com.audiogram.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import rm.com.audiogram.R;
import rm.com.audiogram.entity.Record;
import rm.com.audiowave.AudioWaveView;

/**
 * Created by alex
 */

public final class RecordHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.audio_button_play) ImageView play;
  @BindView(R.id.audio_wave) AudioWaveView wave;

  public RecordHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public final void bind(Record item) {
    wave.setScaledData(new byte[0]);
    wave.setProgress(0);
    wave.setRawData(item.raw);
  }
}
