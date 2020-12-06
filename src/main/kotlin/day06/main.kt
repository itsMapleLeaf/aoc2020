package day06

import helpers.ceil
import helpers.floor
import helpers.lerp
import java.io.File
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow


fun main() {
    val input = File("day06.input.txt").readText()

    val answerGroups = input.split(Regex("\r?\n\r?\n"))

    println(answerGroups
        .map { it.filter(Char::isLetter).toSet().size }
        .sum()
    )

    println(answerGroups
        .map {
            it.split(Regex("\r?\n"))
                .map(CharSequence::toSet)
                .reduce { result, answerSet -> answerSet.intersect(result) }
                .size
        }
        .sum()
    )
}
