package rm.com.audiogram

import android.os.Process
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by alex
 */
val MEDIA_THREAD: ExecutorService = Executors.newSingleThreadExecutor()
val BACKGROUND_THREAD: ExecutorService = Executors.newSingleThreadExecutor { r ->
	object : Thread(r) {
		override fun run() {
			Process.setThreadPriority(Process.THREAD_PRIORITY_DEFAULT + Process.THREAD_PRIORITY_LESS_FAVORABLE)
			super.run()
		}
	}
}

fun runnableOf(task: () -> Unit): Runnable = Runnable { task() }

fun inWorker(task: () -> Unit) {
	SAMPLER_THREAD.submit { task() }
}

fun inMediaWorker(task: () -> Unit) {
	MEDIA_THREAD.submit { task() }
}

fun inUI(task: () -> Unit) {
	MAIN_THREAD.post { task() }
}

fun inUiDelayed(delay: Long, task: () -> Unit) {
	MAIN_THREAD.postDelayed({ task() }, delay)
}