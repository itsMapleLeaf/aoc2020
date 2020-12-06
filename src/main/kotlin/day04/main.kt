package day04

import helpers.doubleLineRegex
import java.io.File

typealias Passport = Map<String, String>
typealias ValidateFn = (String) -> Boolean

data class Validation(val name: String, val value: String?, val isValid: Boolean)

val requiredFields = mapOf(
    "byr" to validateYear(1920..2002),
    "iyr" to validateYear(2010..2020),
    "eyr" to validateYear(2020..2030),
    "hgt" to ::validateHeight,
    "hcl" to { Regex("^#[0-9a-f]{6}$").matches(it) },
    "ecl" to { it in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
    "pid" to { Regex("^\\d{9}$").matches(it) },
)

fun validateYear(range: IntRange): ValidateFn =
    { it.length == 4 && it.toIntOrNull() in range }

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

fun passportFromString(passportString: String): Passport =
    passportString
        .split(Regex("\\s+"))
        .map(::pairFromFieldString)
        .toMap()

fun pairFromFieldString(fieldString: String): Pair<String, String> {
    val (name, value) = fieldString.split(":")
    return Pair(name, value)
}

fun Passport.hasRequiredFields() =
    keys.containsAll(requiredFields.keys)

fun Passport.isValid() = requiredFields
    .map { (fieldName, validateFn) ->
        val value = get(fieldName)
        val isValid = value != null && validateFn(value)
        Validation(fieldName, value, isValid)
    }
    .filter { !it.isValid }
    .onEach { println("invalid field ${it.name}: ${it.value ?: "<missing>"}") }
    .isEmpty()

fun main() {
    val passports: List<Passport> = File("day04.input.txt").readText()
        .split(doubleLineRegex)
        .map(::passportFromString)

    println(passports.count { it.hasRequiredFields() })
    println(passports.count { it.isValid() })
}
