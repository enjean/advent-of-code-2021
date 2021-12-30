package day5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day5Test {
    @Test
    fun `count overlaps`() {
        val input = listOf(
            Line(Coordinate(0, 9), Coordinate(5, 9)),
            Line(Coordinate(8,0), Coordinate(0,8)),
            Line(Coordinate(9,4), Coordinate(3,4)),
            Line(Coordinate(2,2), Coordinate(2,1)),
            Line(Coordinate(7,0), Coordinate(7,4)),
            Line(Coordinate(6,4), Coordinate(2,0)),
            Line(Coordinate(0,9), Coordinate(2,9)),
            Line(Coordinate(3,4), Coordinate(1,4)),
            Line(Coordinate(0,0), Coordinate(8,8)),
            Line(Coordinate(5,5), Coordinate(8,2)),
        )

        val result = countOverlappingPoints(input)

        assertEquals(5, result)
    }
}