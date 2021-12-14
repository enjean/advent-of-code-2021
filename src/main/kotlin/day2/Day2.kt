package day2

import util.ParseUtil


fun main() {
    val commands = ParseUtil.inputLines(2)
        .map { line ->
            val parts = line.split(" ")
            val direction = when(parts[0]) {
                "forward" -> Direction.FORWARD
                "down" -> Direction.DOWN
                "up" -> Direction.UP
                else -> throw IllegalArgumentException("Unknown command $line")
            }
            Command(direction, parts[1].toInt())
        }

    val startingPosition = SubLocation(horizontalPosition = 0, depth = 0)

    val endingPosition = commands.fold(startingPosition) { position, command ->
        position.applyCommand(command)
    }

    println("Part 1 = ${endingPosition.horizontalPosition * endingPosition.depth}")
}
