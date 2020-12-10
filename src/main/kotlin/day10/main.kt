package day10

import java.io.File

fun main() {
    val joltages = File("day10.input.txt").readLines().mapNotNull { it.toIntOrNull() }

    // part 1
    val adapters = joltages.sorted()
    val chain = listOf(0) + adapters + (adapters.last() + 3)

    val differences = chain
        .dropLast(1)
        .mapIndexed { index, joltage -> chain[index + 1] - joltage }

    println(differences.countOf(1) * differences.countOf(3))
}

fun <T> List<T>.countOf(value: T) = count { it == value }