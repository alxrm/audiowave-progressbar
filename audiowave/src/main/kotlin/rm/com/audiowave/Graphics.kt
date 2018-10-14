package rm.com.audiowave

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.RectF
import android.view.View

/**
 * Created by alex
 */


internal fun View.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

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

internal fun rectFOf(left: Int, top: Int, right: Int, bottom: Int) =
    RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())

internal fun Int.withAlpha(alpha: Int): Int {
  require(alpha in 0x00..0xFF)
  return this and 0x00FFFFFF or (alpha shl 24)
}

internal fun Float.clamp(min: Float, max: Float) = Math.min(max, Math.max(this, min))

internal fun Bitmap.inCanvas(): Canvas = Canvas(this)

internal fun Bitmap?.safeRecycle() =
    if (this != null && !isRecycled) recycle() else Unit

internal fun Bitmap?.flush() = this?.eraseColor(0)

internal fun Bitmap?.fits(neededW: Int, neededH: Int): Boolean =
    this?.let { it.height == neededH && it.width == neededW } ?: false
