package day02

import java.io.File

private val input = File("day02.input.txt").readLines()

private data class PasswordLine(
    val firstNumber: Int,
    val secondNumber: Int,
    val requiredLetter: Char,
    val password: String,
) {
    companion object {
        fun fromString(line: String): PasswordLine {
            val regex = Regex("(\\d+)-(\\d+)\\s+([a-z]):\\s+([a-z]+)")
            val match = regex.find(line) ?: error("invalid line $line")
            val (_, firstNumber, secondNumber, requiredLetter, password) = match.groupValues

            return PasswordLine(
                firstNumber.toInt(),
                secondNumber.toInt(),
                requiredLetter[0],
                password,
            )
        }
    }
}

private fun firstValidPasswordsCount() = input.map(PasswordLine::fromString)
    .count { line ->
        val minCount = line.firstNumber
        val maxCount = line.secondNumber
        val count = line.password.count { it == line.requiredLetter }
        count in minCount..maxCount
    }

private fun secondValidPasswordsCount() = input.map(PasswordLine::fromString)
    .count { line ->
        val firstMatches = line.password[line.firstNumber - 1] == line.requiredLetter
        val secondMatches = line.password[line.secondNumber - 1] == line.requiredLetter
        firstMatches != secondMatches
    }

fun main() {
    println(firstValidPasswordsCount())
    println(secondValidPasswordsCount())
}
