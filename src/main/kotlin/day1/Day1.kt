package day1

import util.ParseUtil

fun countIncreases(depthMeasurements: List<Int>): Int =
    depthMeasurements.drop(1).fold(depthMeasurements.first() to 0) { acc, value ->
        val newCount = if (value > acc.first) acc.second + 1 else acc.second
        value to newCount
    }.second

fun countIncreasesOverSlidingWindows(depthMeasurements: List<Int>): Int =
    depthMeasurements.mapIndexedNotNull { index, measurement ->
        if (index == 0 || index == 1) {
            null
        } else {
            measurement + depthMeasurements[index - 1] + depthMeasurements[index - 2]
        }
    }.let { windowValues -> countIncreases(windowValues) }

fun main() {
    val depthMeasurements = ParseUtil.inputLines(1).map { it.toInt() }

    val numIncreases = countIncreases(depthMeasurements)
    println("Part 1 = $numIncreases")

    val numIncreasesOverWindows = countIncreasesOverSlidingWindows(depthMeasurements)
    println("Part 2 = $numIncreasesOverWindows")
}