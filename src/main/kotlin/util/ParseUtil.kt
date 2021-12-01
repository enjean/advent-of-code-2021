package util

import java.io.File

object ParseUtil {
    fun inputLines(day: Int) =
        File("src/main/resources/day$day/input.txt").readLines()
}