package day13

import java.io.File
import kotlin.math.ceil

fun main() {
    val (timestamp, busses) = File("day13.input.txt").readLines().let { lines ->
        val timestamp = lines[0].toInt()
        val busses = lines[1].split(",").mapNotNull { it.toIntOrNull() }
        timestamp to busses
    }

    val upcomingTimes = busses.map { id -> id to timestamp.roundToNextHighestMultipleOf(id) }
    val (id, earliestTime) = upcomingTimes.minByOrNull { (_, time) -> time } ?: error("lol")

    println(id * (earliestTime - timestamp))
}

fun Number.roundToNextHighestMultipleOf(num: Number) =
    ceil(toDouble() / num.toDouble()) * num.toDouble()