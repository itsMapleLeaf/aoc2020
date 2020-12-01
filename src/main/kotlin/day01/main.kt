package day01

import helpers.combinations
import helpers.product
import java.io.File

fun main() {
	val input = File("day01.input.txt").readLines().map { it.toInt() }

	fun find2020SumProduct(combinationSize: Int) =
		input.combinations(combinationSize).find { it.sum() == 2020 }?.product()

	println("answer 1: ${find2020SumProduct(2)}")
	println("answer 2: ${find2020SumProduct(3)}")
}
