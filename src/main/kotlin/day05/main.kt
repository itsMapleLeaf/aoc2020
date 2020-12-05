package day05

import helpers.lerp
import java.io.File
import kotlin.math.ceil
import kotlin.math.floor


fun getBoardingPassSeatId(pass: String): Int {
    val row = run {
        var min = 0
        var max = 127

        for (char in pass.slice(0..6)) {
            when (char) {
                'B' -> min = ceil(lerp(min.toDouble(), max.toDouble(), 0.5)).toInt()
                'F' -> max = floor(lerp(min.toDouble(), max.toDouble(), 0.5)).toInt()
            }
        }

        min
    }

    val col = run {
        var min = 0
        var max = 7

        for (char in pass.slice(7..9)) {
            when (char) {
                'R' -> min = ceil(lerp(min.toDouble(), max.toDouble(), 0.5)).toInt()
                'L' -> max = floor(lerp(min.toDouble(), max.toDouble(), 0.5)).toInt()
            }
        }

        min
    }

    return row * 8 + col
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
