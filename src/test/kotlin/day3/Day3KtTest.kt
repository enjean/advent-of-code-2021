package day3

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day3KtTest {

    @Test
    fun `calculateRates example`() {
        val diagnosticReport = listOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010",
        )

        val powerConsumptionData = calculateRates(diagnosticReport)

        assertEquals(22, powerConsumptionData.gammaRate)
        assertEquals(9, powerConsumptionData.epsilonRate)
    }
}