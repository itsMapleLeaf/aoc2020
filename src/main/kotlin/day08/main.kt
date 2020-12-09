package day08

import java.io.File

data class ProgramResult(
    val status: ProgramStatus,
    val accumulator: Int,
)

enum class ProgramStatus {
    Looped,
    Terminated,
}

data class Instruction(
    val operation: Operation,
    val value: Int,
)

enum class Operation {
    Accumulate,
    Jump,
    Noop,
}

fun createInstruction(string: String): Instruction {
    val (operation, value) = string.split(" ")
    return Instruction(
        when (operation) {
            "acc" -> Operation.Accumulate
            "jmp" -> Operation.Jump
            "nop" -> Operation.Noop
            else -> error("invalid op '$operation'")
        },
        value.toInt()
    )
}

fun runProgram(instructions: List<Instruction>): ProgramResult {
    var pointer = 0
    var accumulator = 0
    val visitedLines = mutableSetOf<Int>()

    while (true) {
        visitedLines.add(pointer)

        val (operation, value) = instructions[pointer]
        when (operation) {
            Operation.Accumulate -> {
                accumulator += value
                pointer += 1
            }
            Operation.Jump -> {
                pointer += value
            }
            Operation.Noop -> {
                pointer += 1
            }
        }

        if (visitedLines.contains(pointer)) {
            return ProgramResult(ProgramStatus.Looped, accumulator)
        }

        if (pointer == instructions.size) {
            return ProgramResult(ProgramStatus.Terminated, accumulator)
        }
    }
}

// how is this not built in am i blind wtf
fun <T> List<T>.replaceAt(index: Int, value: T) =
    mapIndexed { originalIndex, currentValue ->
        if (originalIndex == index) value else currentValue
    }

fun main() {
    val instructions = File("day08.input.txt").readLines().map(::createInstruction)

    // part 1
    println(runProgram(instructions).accumulator)

    // part 2
    fun Instruction.patch() = when (operation) {
        Operation.Jump -> copy(operation = Operation.Noop)
        Operation.Noop -> copy(operation = Operation.Jump)
        else -> this
    }

    val patchedPrograms = instructions
        .asSequence()
        .withIndex()
        .filter { it.value.operation != Operation.Accumulate }
        .map { (index, instruction) -> instructions.replaceAt(index, instruction.patch()) }

    val firstTerminatedResult = patchedPrograms
        .map(::runProgram)
        .first { it.status == ProgramStatus.Terminated }

    println(firstTerminatedResult.accumulator)
}
