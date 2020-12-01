package helpers

fun <T> Iterable<T>.combinations(size: Int): Sequence<Set<T>> {
    if (size < 1) {
        return sequence { }
    }

    if (size == 1) {
        return asSequence().map(::setOf)
    }

    return sequence {
        for ((index, first) in withIndex()) {
            for (rest in drop(index).combinations(size - 1)) {
                val combination = setOf(first) + rest
                if (combination.size == size) yield(combination)
            }
        }
    }
}

fun Iterable<Int>.product(): Int =
    reduce { result, num -> result * num }
