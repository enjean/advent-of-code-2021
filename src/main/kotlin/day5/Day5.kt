package day5

import util.ParseUtil
import kotlin.math.max
import kotlin.math.min

fun parseLine(inputLine: String): Line {
    val coordSetParts = inputLine.split(" -> ")
    return Line(parseCoordinate(coordSetParts[0]), parseCoordinate(coordSetParts[1]))
}

private fun parseCoordinate(coordString: String): Coordinate {
    val parts = coordString.split(",")
    return Coordinate(parts[0].toInt(), parts[1].toInt())
}

fun countOverlappingPoints(lines: List<Line>): Int {
    val horizontalAndVerticalLines = lines.filter { line ->
        line.start.x == line.end.x || line.start.y == line.end.y
    }
    val pointCounts = mutableMapOf<Coordinate, Int>()
    horizontalAndVerticalLines.forEach { line ->
        line.linePoints().forEach { point ->
            val currentCount = pointCounts[point] ?: 0
            pointCounts[point] = currentCount +1
        }
    }
    return pointCounts.count { it.value > 1 }
}

private fun Line.linePoints() =
    if (start.x == end.x) {
        (min(start.y, end.y)..max(start.y, end.y)).map { Coordinate(start.x, it) }.toSet()
    } else {
        (min(start.x, end.x)..max(start.x, end.x)).map { Coordinate(it, start.y) }.toSet()
    }

fun main() {
    val lines = ParseUtil.inputLines(5).map(::parseLine)

    val overlapCount = countOverlappingPoints(lines)

    println("Part 1 = $overlapCount")
}