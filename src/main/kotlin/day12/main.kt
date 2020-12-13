package day12

import helpers.Vector
import java.io.File
import kotlin.math.absoluteValue

fun navigateShipSimple(instructions: List<String>): Int {
    var position = Vector.center
    var facing = Vector.right

    for (line in instructions) {
        val action = line[0]
        val value = line.drop(1).toInt()

        when (action) {
            'N' -> position += Vector.up * value
            'S' -> position += Vector.down * value
            'E' -> position += Vector.right * value
            'W' -> position += Vector.left * value
            'L' -> facing = facing.rotate(value / -90)
            'R' -> facing = facing.rotate(value / 90)
            'F' -> position += facing * value
        }
    }

    return position.x.absoluteValue + position.y.absoluteValue
}

fun navigateShip(instructions: List<String>): Int {
    var position = Vector.center
    var waypoint = Vector(10, -1)

    for (line in instructions) {
        val action = line[0]
        val value = line.drop(1).toInt()

        when (action) {
            'N' -> waypoint += Vector.up * value
            'S' -> waypoint += Vector.down * value
            'E' -> waypoint += Vector.right * value
            'W' -> waypoint += Vector.left * value
            'L' -> waypoint = waypoint.rotate(value / -90)
            'R' -> waypoint = waypoint.rotate(value / 90)
            'F' -> position += waypoint * value
        }
    }

    return position.x.absoluteValue + position.y.absoluteValue
}

fun main() {
    val instructions = File("day12.input.txt").readLines()

    // part 1
    println(navigateShipSimple(instructions))

    // part 2
    println(navigateShip(instructions))
}


