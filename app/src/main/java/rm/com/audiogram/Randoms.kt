package rm.com.audiogram

import java.util.*

/**
 * Created by alex
 */

private val RANDOM by lazy { Random() }

fun randomInt(min: Int, max: Int): Int =
		RANDOM.nextInt(max - min + 1) + min

fun randomFloat(min: Int, max: Int): Float = randomInt(min, max).toFloat()

fun randomFloat(min: Float, max: Float): Float = randomInt(min.toInt(), max.toInt()).toFloat()

fun randomBytesOfSize(size: Int = 20): ByteArray = ByteArray(size, { randomInt(-127, 127).toByte() })

fun <T> List<T>.oneOf(): T =
		getOrElse(randomInt(0, lastIndex)) {
			error("Could not get random element from list")
		}

fun <T> Array<T>.oneOf(): T =
		getOrElse(randomInt(0, lastIndex)) {
			error("Could not get random element from array")
		}