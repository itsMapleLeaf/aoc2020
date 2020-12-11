package day11

import helpers.Grid
import helpers.Vector
import helpers.countOf
import helpers.toGrid
import java.io.File

enum class Cell {
    Empty,
    Floor,
    Occupied,
}

fun Char.toCell() = when (this) {
    'L' -> Cell.Empty
    '#' -> Cell.Occupied
    '.' -> Cell.Floor
    else -> error("invalid char $this")
}

tailrec fun simulate(grid: Grid<Cell>): Grid<Cell> {
    val newGrid = grid.mapWithPos { pos, cell ->
        when (cell) {
            Cell.Empty -> {
                val isAdjacentOccupied = Vector.directions.map { grid[pos + it] }.contains(Cell.Occupied)
                if (!isAdjacentOccupied) Cell.Occupied else cell
            }

            Cell.Occupied -> {
                val occupiedCount = Vector.directions.map { grid[pos + it] }.countOf(Cell.Occupied)
                if (occupiedCount >= 4) Cell.Empty else cell
            }

            else -> cell
        }
    }

    return if (grid == newGrid) newGrid else simulate(newGrid)
}

fun main() {
    val grid = File("day11.input.txt").readLines()
        .map { line -> line.map { it.toCell() } }
        .toGrid()

    // part 1
    println(simulate(grid).countOf(Cell.Occupied))
}
