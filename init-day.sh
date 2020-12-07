DAY_NUMBER=$1

touch "./resources/day$DAY_NUMBER.input.txt"

mkdir -p "./src/main/kotlin/day$DAY_NUMBER/"

echo "package day$DAY_NUMBER

import java.io.File

fun main() {
    val input = File(\"day$DAY_NUMBER.input.txt\").readText()
    println(input)
}" >"./src/main/kotlin/day$DAY_NUMBER/main.kt"
