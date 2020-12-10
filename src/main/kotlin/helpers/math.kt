package helpers

fun Iterable<Int>.product(): Int =
    reduce { result, num -> result * num }

fun Iterable<Long>.product(): Long =
    reduce { result, num -> result * num }

fun Collection<Number>.average(): Double =
    map { it.toDouble() }.sum() / size

fun lerp(a: Double, b: Double, delta: Double) =
    b + (a - b) * delta

fun lerp(a: Int, b: Int, delta: Double) =
    b + (a - b) * delta

fun Double.floorToInt(): Int = kotlin.math.floor(this).toInt()
fun Double.ceilToInt(): Int = kotlin.math.floor(this).toInt()