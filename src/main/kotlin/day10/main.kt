package day10

import helpers.product
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

    // part 2
    // thank you reddit people https://www.reddit.com/r/adventofcode/comments/kaqwwa/day_10_part_2_can_someone_help_me_test_my/
    val groups = mutableListOf(listOf(0))

    // split up into groups separated by gaps of three
    // so 1, 2, 5, 6, 7 becomes [[1, 2], [5, 6, 7]]
    for ((index, value) in chain.withIndex().drop(1).dropLast(1)) {
        if (value - chain[index - 1] == 3) {
            groups += listOf(value)
        } else {
            groups[groups.lastIndex] += listOf(value)
        }
    }

    // if we get the number of possible sequences in each group,
    // then multiply them all together,
    // that gets the total combination count
    // math LOL
    println(groups.map { voltageSequenceCount(it.size) }.product())
}

fun <T> List<T>.countOf(value: T) = count { it == value }

fun voltageSequenceCount(n: Int): Long = when (n) {
    1 -> 1
    2 -> 1
    3 -> 2
    4 -> 4
    5 -> 7
    else -> error("this puzzle doesn't have sub-group counts of more than 5 KEKW")
}
