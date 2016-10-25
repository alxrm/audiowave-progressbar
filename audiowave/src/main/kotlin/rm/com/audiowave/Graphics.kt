package rm.com.audiowave

import android.content.Context
import android.graphics.*
import android.view.View

/**
 * Created by alex
 */
internal fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()
internal fun Context.dipF(value: Float): Float = value * resources.displayMetrics.density

internal fun View.dip(value: Int): Int = context.dip(value)
internal fun View.dipF(value: Float): Float = context.dipF(value)

internal fun smoothPaint(color: Int = Color.WHITE): Paint =
		Paint().apply {
			isAntiAlias = true
			this.color = color
		}

internal fun filterPaint(color: Int = Color.BLACK): Paint =
		Paint().apply {
			isAntiAlias = true
			colorFilter = filterOf(color)
		}

internal fun filterOf(color: Int = Color.BLACK) =
		PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

internal inline fun Canvas.transform(crossinline init: Canvas.() -> Unit) {
	save()
	init()
	restore()
}

internal fun rectFOf(left: Int, top: Int, right: Int, bottom: Int) = RectF(
		left.toFloat()
		, top.toFloat()
		, right.toFloat()
		, bottom.toFloat()
)

internal fun Int.withAlpha(alpha: Int): Int {
	require(alpha in 0x00..0xFF)
	return this and 0x00FFFFFF or (alpha shl 24)
}

internal fun Bitmap.inCanvas(): Canvas = Canvas(this)

internal fun Bitmap?.safeRecycle() =
		if (this != null && !isRecycled) recycle() else Unit

internal fun Bitmap?.flush() = this?.eraseColor(0)

internal fun Bitmap?.fits(neededW: Int, neededH: Int): Boolean =
		this?.let { it.height == neededH && it.width == neededW } ?: false
