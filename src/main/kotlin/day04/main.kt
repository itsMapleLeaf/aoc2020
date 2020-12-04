package day04

import java.io.File

typealias Passport = Map<String, String>

fun passportFromString(passportString: String): Passport =
    passportString
        .split(Regex("\\s+"))
        .map(::pairFromFieldString)
        .toMap()

fun pairFromFieldString(fieldString: String): Pair<String, String> {
    val (name, value) = fieldString.split(":")
    return Pair(name, value)
}

val requiredFields = mapOf(
    "byr" to { it.length == 4 && it.toIntOrNull() in 1920..2002 },
    "iyr" to { it.length == 4 && it.toIntOrNull() in 2010..2020 },
    "eyr" to { it.length == 4 && it.toIntOrNull() in 2020..2030 },
    "hgt" to ::validateHeight,
    "hcl" to { Regex("^#[0-9a-f]{6}$").matches(it) },
    "ecl" to { it in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
    "pid" to { Regex("^\\d{9}$").matches(it) },
)

fun Passport.hasRequiredFields() =
    keys.containsAll(requiredFields.keys)

fun Passport.isValid() = requiredFields.all { (fieldName, getIsValid) ->
    val value = this[fieldName] ?: return@all false
    val isValid = getIsValid(value)
    if (!isValid) {
        println("invalid field $fieldName:$value")
    }
    isValid
}

fun validateHeight(heightValue: String): Boolean {
    val result = Regex("^(\\d+)(cm|in)$").find(heightValue) ?: return false
    val (_, valueString, unit) = result.groupValues

    val value = valueString.toIntOrNull() ?: return false

    val range = when (unit) {
        "cm" -> 150..193
        "in" -> 59..76
        else -> return false
    }

    return value in range
}

fun main() {
    val passports: List<Passport> = File("day04.input.txt").readText()
        .split(Regex("\r?\n\r?\n"))
        .map(::passportFromString)

    println(passports.count { it.hasRequiredFields() })
    println(passports.count { it.isValid() })
}
