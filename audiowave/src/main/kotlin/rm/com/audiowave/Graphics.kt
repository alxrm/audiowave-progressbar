package rm.com.audiowave

import android.content.Context
import android.graphics.*
import android.view.View

/**
 * Created by alex
 */
fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()
fun View.dip(value: Int): Int = context.dip(value)

fun smoothPaint(color: Int = Color.WHITE): Paint =
		Paint().apply {
			isAntiAlias = true
			this.color = color
		}

fun filterPaint(color: Int = Color.BLACK): Paint =
		Paint().apply {
			isAntiAlias = true
			colorFilter = filterOf(color)
		}

fun filterOf(color: Int = Color.BLACK) =
		PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

inline fun Canvas.transform(crossinline init: Canvas.() -> Unit) {
	save()
	init()
	restore()
}

fun rectOf(left: Int, top: Int, right: Int, bottom: Int) = Rect(left, top, right, bottom)

fun Int.withAlpha(alpha: Int): Int {
	require(alpha in 0x00..0xFF)
	return this and 0x00FFFFFF or (alpha shl 24)
}

fun Bitmap.inCanvas(): Canvas = Canvas(this)

fun Bitmap?.safeRecycle() =
		if (this != null && !isRecycled) recycle() else Unit

fun Bitmap?.flush() = this?.eraseColor(0)

fun Bitmap?.fits(neededW: Int, neededH: Int): Boolean =
		this?.let { it.height == neededH && it.width == neededW } ?: false
