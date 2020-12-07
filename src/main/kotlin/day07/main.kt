package day07

import java.io.File

typealias BagColor = String

data class ChildBag(val color: BagColor, val count: Int)

fun parseChildBagString(childBagString: String): ChildBag? {
    val (_, count, color) = Regex("(\\d+)\\s+(.+)")
        .matchEntire(childBagString.replace(Regex(" bags?\\.?"), ""))
        ?.groupValues
        ?: return null

    return ChildBag(color, count.toInt())
}

fun main() {
    val ruleStrings = File("day07.input.txt").readLines()

    // bag color -> children
    val bags = ruleStrings.map { ruleString ->
        val (bag, childrenString) = ruleString.split(" bags contain ")
        val children = childrenString.split(", ").mapNotNull(::parseChildBagString).toSet()
        bag to children
    }.toMap()

    fun BagColor.canContain(otherColor: BagColor): Boolean =
        bags.getValue(this).any { it.color == otherColor || it.color.canContain(otherColor) }

    fun getContainmentCount(color: BagColor) =
        bags.keys.count { it.canContain(color) }

    fun getChildCount(color: BagColor): Int =
        bags.getValue(color).sumBy { it.count + it.count * getChildCount(it.color) }

    println(getContainmentCount("shiny gold"))
    println(getChildCount("shiny gold"))
}
