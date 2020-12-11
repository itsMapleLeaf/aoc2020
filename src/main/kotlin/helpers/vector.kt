package helpers

data class Vector(val x: Int, val y: Int) {
    companion object {
        val center = Vector(0, 0)

        val left = Vector(-1, 0)
        val right = Vector(1, 0)
        val up = Vector(0, -1)
        val down = Vector(0, 1)

        val upLeft = up + left
        val upRight = up + right
        val downLeft = down + left
        val downRight = down + right

        val cardinals = listOf(left, right, up, down)
        val diagonals = listOf(upLeft, upRight, downLeft, downRight)

        val directions = cardinals + diagonals
    }

    operator fun plus(vec: Vector) = Vector(x + vec.x, y + vec.y)
}