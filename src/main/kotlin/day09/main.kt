package day09

import helpers.combinations
import helpers.highest
import helpers.lowest
import java.io.File

fun isXMASNumberValid(numbers: List<Long>, index: Int, preambleLength: Int) =
    numbers
        .slice((index - preambleLength) until index)
        .combinations(2)
        .any { (a, b) -> a + b == numbers[index] }

fun main() {
    val numbers = File("day09.input.txt").readLines().map { it.toLong() }

    // part 1
    val invalidEntry = numbers
        .withIndex()
        .drop(25)
        .first { !isXMASNumberValid(numbers, it.index, 25) }

    println(invalidEntry.value)

    // part 2
    println(numbers.indices
        .combinations(2)
        .filter { (start, end) -> end - start >= 2 }
        .map { (start, end) -> numbers.subList(start, end) }
        .first { numRange -> numRange.sum() == invalidEntry.value }
        .run { lowest() + highest() }
    )
}
