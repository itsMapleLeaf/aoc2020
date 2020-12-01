package day01

import helpers.combinations
import helpers.product
import java.io.File

fun main() {
	val input = File("day01.input.txt").readLines().map { it.toInt() }
	val answer1 = input.combinations(2).find { it.sum() == 2020 }?.product()
	val answer2 = input.combinations(3).find { it.sum() == 2020 }?.product()

	println("answer 1: $answer1")
	println("answer 2: $answer2")
}
