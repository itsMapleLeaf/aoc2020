package day04

import helpers.Vector
import helpers.product
import java.io.File
import java.util.regex.Pattern

private typealias PassportField = Pair<String, String>

private val PassportField.name get() = first
private val PassportField.value get() = second

private typealias Passport = List<PassportField>

private val Passport.fieldNames get() = map { it.name }.toSet()

val passports = File("day04.input.txt").readText()
    .split(Regex("\r?\n\r?\n"))
    .map { passportString ->
        val fieldStrings = passportString.split(Regex("\\s+"))
        fieldStrings.map { fieldString ->
            val (name, value) = fieldString.split(':')
            PassportField(name, value)
        }
    }

val fieldNames = listOf(
    "byr",
    "iyr",
    "eyr",
    "hgt",
    "hcl",
    "ecl",
    "pid",
//    "cid",
)

typealias Validator = (String) -> Boolean

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

val fieldValidations = mapOf<String, Validator>(
    "byr" to { it.length == 4 && it.toIntOrNull() in 1920..2002 },
    "iyr" to { it.length == 4 && it.toIntOrNull() in 2010..2020 },
    "eyr" to { it.length == 4 && it.toIntOrNull() in 2020..2030 },
    "hgt" to ::validateHeight,
    "hcl" to { Regex("^#[0-9a-f]{6}$").matches(it) },
    "ecl" to { it in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
    "pid" to { Regex("^\\d{9}$").matches(it) },
)

fun main() {
    println(
        passports.count { passport ->
            passport.fieldNames.containsAll(fieldNames)
        }
    )

    println(
        passports.count { fields ->
            fieldValidations.all { (fieldName, getIsValid) ->
                val field = fields.find { it.name == fieldName } ?: return@all false
                val isValid = getIsValid(field.value)
                if (!isValid) {
                    println("invalid field $field")
                }
                isValid
            }
        }
    )
}
