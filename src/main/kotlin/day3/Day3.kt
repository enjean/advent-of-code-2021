package day3

import util.ParseUtil

data class PowerConsumptionData(
    val gammaRate: Int,
    val epsilonRate: Int
)

fun calculateRates(diagnosticReport: List<String>): PowerConsumptionData {
    val is1MostCommon = diagnosticReport.first().indices.map { index ->
        val characters = diagnosticReport.map { it[index] }
        characters.count { it == '1' } > (diagnosticReport.size / 2)
    }
    val gammaBinary = is1MostCommon.map { if(it) '1' else '0' }.joinToString(separator = "")
    val epsilonBinary = is1MostCommon.map { if(it) '0' else '1' }.joinToString(separator = "")
    return PowerConsumptionData(gammaBinary.toInt(radix = 2), epsilonBinary.toInt(radix = 2))
}

fun main() {
    val diagnosticReport = ParseUtil.inputLines(3)

    val powerConsumptionData = calculateRates(diagnosticReport)
    println("Part 1 = ${powerConsumptionData.gammaRate * powerConsumptionData.epsilonRate}")
}