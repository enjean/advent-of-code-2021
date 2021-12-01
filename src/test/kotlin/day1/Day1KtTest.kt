package day1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day1KtTest {

    @Test
    fun `Part 1`() {
        val input = listOf(
            199,
            200,
            208,
            210,
            200,
            207,
            240,
            269,
            260,
            263
        )

        val result = countIncreases(input)

        assertEquals(7, result)
    }
}