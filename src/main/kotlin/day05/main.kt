package day05

import helpers.ceil
import helpers.floor
import helpers.lerp
import java.io.File
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow

fun <T> binaryPartition(sequence: Collection<T>, rightValue: T, leftValue: T): Double {
    var min = 0.0
    var max = 2.0.pow(sequence.size)

    for (char in sequence) {
        when (char) {
            rightValue -> min = ceil(lerp(min, max, 0.5))
            leftValue -> max = floor(lerp(min, max, 0.5))
        }
    }

    return min
}

fun getBoardingPassSeatId(pass: String): Int {
    val row = binaryPartition(pass.slice(0..6).toList(), 'B', 'F')
    val col = binaryPartition(pass.slice(7..9).toList(), 'R', 'L')
    return row.toInt() * 8 + col.toInt()
}

fun main() {
    val passes = File("day05.input.txt").readLines()
    val passIds = passes.map(::getBoardingPassSeatId).sorted()

    println(passIds.maxOrNull())

    println(passIds
        .withIndex()
        .find { (index, id) -> passIds[index + 1] != id + 1 }
        ?.let { it.value + 1 }
    )
}
