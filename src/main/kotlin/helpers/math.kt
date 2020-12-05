package helpers

fun Iterable<Int>.product(): Int =
    reduce { result, num -> result * num }

fun Iterable<Long>.product(): Long =
    reduce { result, num -> result * num }

fun lerp(a: Double, b: Double, delta: Double) =
    b + (a - b) * delta