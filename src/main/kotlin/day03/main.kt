package day03

import helpers.Vector
import java.io.File

private val input = File("day03.input.txt")
    .readLines()
    .map { line -> line.map { char -> if (char == '#') 1 else 0 } }

private fun countTrees(slope: Vector, pos: Vector = Vector(0, 0)): Long =
    if (pos.y >= input.size)
        0
    else
        input[pos.y][pos.x % input[0].size] + countTrees(slope, pos + slope)

fun main() {
    println(countTrees(Vector(3, 1)))

    val slopes = listOf(
        Vector(1, 1),
        Vector(3, 1),
        Vector(5, 1),
        Vector(7, 1),
        Vector(1, 2),
    )
    println(slopes.fold(1L) { total, slope -> total * countTrees(slope) })
}
