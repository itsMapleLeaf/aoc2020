package helpers

data class Grid<T>(val cells: Map<Vector, T> = emptyMap()) : Collection<T> {
    override val size get() = cells.size

    override fun contains(element: T): Boolean {
        return cells.containsValue(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return elements.all { cells.containsValue(it) }
    }

    override fun isEmpty(): Boolean {
        return cells.isEmpty()
    }

    override fun iterator(): Iterator<T> {
        return cells.values.iterator()
    }

    operator fun get(vector: Vector): T? = cells[vector]

    override fun toString(): String {
        val lines = hashMapOf<Int, HashMap<Int, String>>()

        for ((pos, item) in cells) {
            lines[pos.y] = lines
                .getOrElse(pos.y) { hashMapOf() }
                .apply { set(pos.x, item.toString()) }
        }

        // i'll clean this up when i feel like it
        return (0..lines.keys.highest()).map { key -> lines[key] ?: hashMapOf() }.joinToString("\n") {
            (0..it.keys.highest()).joinToString("") { key -> it[key].toString() }
        }
    }

    fun mapWithPos(block: (Vector, T) -> T) =
        Grid(cells.entries
            .map { it.key to block(it.key, it.value) }
            .toMap())
}

fun <T> Iterable<Iterable<T>>.toGrid(): Grid<T> {
    val cells = flatMapIndexed { y, list ->
        list.mapIndexed { x, item ->
            Vector(x, y) to item
        }
    }
    return Grid(cells.toMap())
}
