package helpers

data class Vector(val x: Int, val y: Int) {
    operator fun plus(vec: Vector) = Vector(x + vec.x, y + vec.y)
}