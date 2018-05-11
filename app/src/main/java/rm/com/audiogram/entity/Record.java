package rm.com.audiogram.entity;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by alex
 */

public final class Record {

  public final byte[] raw;

  public static List<Record> generateDummyList(@IntRange(from = 0, to = 100) int size) {
    final ArrayList<Record> records = new ArrayList<>(size);
    final Random rnd = new Random();

    for (int i = 0; i < size; i++) {
      records.add(generateDummyItem(1024 * 200, rnd));
    }

    return records;
  }

  private static Record generateDummyItem(int length, @NonNull Random rnd) {
    final byte[] raw = new byte[length];
    rnd.nextBytes(raw);

    return new Record(raw);
  }

  private Record(byte[] raw) {
    this.raw = raw;
  }
}
