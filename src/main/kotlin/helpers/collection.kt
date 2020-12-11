package helpers

fun <T> Sequence<T>.combinations(size: Int): Sequence<List<T>> {
    if (size < 1) {
        return emptySequence()
    }

    if (size == 1) {
        return asSequence().map { listOf(it) }
    }

    return sequence {
        for ((index, first) in withIndex()) {
            for (rest in drop(index).combinations(size - 1)) {
                val combination = setOf(first) + rest
                if (combination.size == size) yield(combination.toList())
            }
        }
    }
}

fun <T> Iterable<T>.combinations(size: Int) =
    asSequence().combinations(size)

fun <T> Iterable<T>.countOf(value: T) = count { it == value }

// WHY IS THIS NOT BUILT IN
fun <T : Comparable<T>> Iterable<T>.lowest() = minOrNull() ?: error("iterable is empty")
fun <T : Comparable<T>> Iterable<T>.highest() = maxOrNull() ?: error("iterable is empty")