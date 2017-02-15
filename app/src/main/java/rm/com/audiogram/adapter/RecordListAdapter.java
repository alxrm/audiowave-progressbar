package rm.com.audiogram.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import rm.com.audiogram.R;
import rm.com.audiogram.entity.Record;
import rm.com.audiogram.holder.RecordHolder;

/**
 * Created by alex
 */

public final class RecordListAdapter extends RecyclerView.Adapter<RecordHolder> {

  private List<Record> data = new ArrayList<>();

  @Override public RecordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View itemView =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_audio, parent, false);
    return new RecordHolder(itemView);
  }

  @Override public void onBindViewHolder(RecordHolder holder, int position) {
    holder.bind(data.get(position));
  }

  public final void updateData(@NonNull List<Record> nextData) {
    this.data = nextData;
    notifyDataSetChanged();
  }

  @Override public int getItemCount() {
    return this.data.size();
  }
}
