package day11

import helpers.Grid
import helpers.Vector
import helpers.countOf
import helpers.toGrid
import java.io.File

const val CELL_EMPTY = 'L'
const val CELL_OCCUPIED = '#'
const val CELL_FLOOR = '.'

tailrec fun simulate(grid: Grid<Char>): Grid<Char> {
    val newGrid = grid.mapWithPos { pos, cell ->
        val occupiedCount by lazy {
            Vector.directions.map { grid[pos + it] }.countOf(CELL_OCCUPIED)
        }

        when (cell) {
            CELL_EMPTY -> if (occupiedCount == 0) CELL_OCCUPIED else cell
            CELL_OCCUPIED -> if (occupiedCount >= 4) CELL_EMPTY else cell
            else -> cell
        }
    }

    return if (grid == newGrid) newGrid else simulate(newGrid)
}

// generate a sequence that returns vectors going in `dir` from `center`
// e.g. raycastFrom(Vector(1, 1), Vector(1, 0)) becomes [(2, 1), (3, 1), (4, 1), ...]
fun raycast(center: Vector, dir: Vector) =
    generateSequence(center + dir) { it + dir }

tailrec fun simulateWithRaycast(grid: Grid<Char>): Grid<Char> {
    val newGrid = grid.mapWithPos { pos, cell ->
        val occupiedCount by lazy {
            Vector.directions.count { dir ->
                val lineOfSight = raycast(pos, dir).map { grid[it] }
                lineOfSight.first { it != CELL_FLOOR } == CELL_OCCUPIED
            }
        }

        when (cell) {
            CELL_EMPTY -> if (occupiedCount == 0) CELL_OCCUPIED else cell
            CELL_OCCUPIED -> if (occupiedCount >= 5) CELL_EMPTY else cell
            else -> cell
        }
    }

    return if (grid == newGrid) newGrid else simulateWithRaycast(newGrid)
}

fun main() {
    val grid = File("day11.input.txt").readLines()
        .map { line -> line.toList() }
        .toGrid()

    // part 1
    println(simulate(grid).countOf(CELL_OCCUPIED))

    // part 2
    println(simulateWithRaycast(grid).countOf(CELL_OCCUPIED))
}
