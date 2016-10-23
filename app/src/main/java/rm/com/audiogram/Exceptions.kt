package rm.com.audiogram

import android.util.Log

/**
 * Created by alex
 */
inline fun <T> tryExc(lazy: () -> T, recover: (Exception) -> T): T =
		try {
			lazy()
		} catch(e: Exception) {
			recover(e)
		}

inline fun <T> tryLog(lazy: () -> T): T? =
		tryExc(lazy) {
			Log.e("ERROR", it.message)
			null
		}

inline fun <T> tryOptional(lazy: () -> T): T? =
		tryExc(lazy) { null }