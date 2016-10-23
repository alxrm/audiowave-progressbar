package rm.com.audiogram

import android.os.Environment
import android.os.Looper
import android.os.StrictMode
import java.io.File

/**
 * Created by alex
 */

fun assertWorkerThread(): Unit =
		StrictMode.noteSlowCall("slow")

fun isMainThread(): Boolean =
		Looper.myLooper() == Looper.getMainLooper()

fun assertMainThread(): Unit =
		when {
			BuildConfig.DEBUG && !isMainThread() -> error("wrong thread, buddy")
			else -> Unit
		}

val extDir: File
	get() = Environment.getExternalStorageDirectory()