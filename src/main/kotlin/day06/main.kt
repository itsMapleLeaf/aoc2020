package day06

import helpers.doubleLineRegex
import helpers.singleLineRegex
import java.io.File

fun main() {
    val input = File("day06.input.txt").readText()

    val answerGroups = input.split(doubleLineRegex)

    println(answerGroups
        .map { it.filter(Char::isLetter).toSet().size }
        .sum()
    )

    println(answerGroups
        .map {
            it.split(singleLineRegex)
                .map(CharSequence::toSet)
                .reduce { result, answerSet -> answerSet.intersect(result) }
                .size
        }
        .sum()
    )
}
