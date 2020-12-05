package helpers

fun Iterable<Int>.product(): Int =
    reduce { result, num -> result * num }

fun Iterable<Long>.product(): Long =
    reduce { result, num -> result * num }

fun lerp(a: Double, b: Double, delta: Double) =
    b + (a - b) * delta

fun lerp(a: Int, b: Int, delta: Double) =
    b + (a - b) * delta

fun Double.floor(): Int = kotlin.math.floor(this).toInt()
fun Double.ceil(): Int = kotlin.math.floor(this).toInt()