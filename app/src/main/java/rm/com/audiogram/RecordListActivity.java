package rm.com.audiogram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import rm.com.audiogram.adapter.RecordListAdapter;
import rm.com.audiogram.entity.Record;
import rm.com.audiogram.holder.RecordHolder;
import rm.com.audiowave.AudioWaveView;

public final class RecordListActivity extends AppCompatActivity {

  @BindView(R.id.audio_list) RecyclerView audioList;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_audio_list);
    ButterKnife.bind(this);

    final RecordListAdapter adapter = new RecordListAdapter();
    audioList.setAdapter(adapter);

    adapter.updateData(Record.generateDummyList(50));
  }
}
