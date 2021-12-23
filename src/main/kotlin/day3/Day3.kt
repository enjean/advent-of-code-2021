package day3

import util.ParseUtil

data class PowerConsumptionData(
    val gammaRate: Int,
    val epsilonRate: Int
) {
    val powerConsumption = gammaRate * epsilonRate
}

fun calculatePowerConsumptionData(diagnosticReport: List<String>): PowerConsumptionData {
    val rates = diagnosticReport.first().indices.fold("" to "") { ratesSoFar, index ->
        val is1MostCommon = diagnosticReport.countOfCharAtIndex('1', index) > (diagnosticReport.size / 2)
        val gammaDigit = if (is1MostCommon) '1' else '0'
        val epsilonDigit = if (is1MostCommon) '0' else '1'
        ratesSoFar.first + gammaDigit to ratesSoFar.second + epsilonDigit
    }
    return PowerConsumptionData(rates.first.toInt(radix = 2), rates.second.toInt(radix = 2))
}

data class LifeSupportData(
    val oxygenGeneratorRating: Int,
    val c02ScrubberRating: Int
) {
    val lifeSupportRating = oxygenGeneratorRating * c02ScrubberRating
}

fun calculateLifeSupportData(diagnosticReport: List<String>): LifeSupportData {
    val oxygenGeneratorRating = lifeSupportStep(findMostCommon = true, index = 0, remainingInputs = diagnosticReport)
    val c02ScrubberRating = lifeSupportStep(findMostCommon = false, index = 0, remainingInputs = diagnosticReport)
    return LifeSupportData(oxygenGeneratorRating, c02ScrubberRating)
}

private fun lifeSupportStep(findMostCommon: Boolean, index: Int, remainingInputs: List<String>): Int {
    if (remainingInputs.size == 1) return remainingInputs.single().toInt(2)
    val countOf1 = remainingInputs.countOfCharAtIndex('1', index)
    val countOf0 = remainingInputs.size - countOf1
    val valueToKeep = if(countOf1 >= countOf0) {
        if(findMostCommon) '1' else '0'
    } else {
        if(findMostCommon) '0' else '1'
    }
    val newRemaining = remainingInputs.filter { it[index] == valueToKeep }
    return lifeSupportStep(findMostCommon, index + 1, newRemaining)
}

private fun List<String>.countOfCharAtIndex(char: Char, index: Int) =
    this.map { it[index] }.count { it == char }

fun main() {
    val diagnosticReport = ParseUtil.inputLines(3)

    val powerConsumptionData = calculatePowerConsumptionData(diagnosticReport)
    println("Part 1 = ${powerConsumptionData.powerConsumption}")

    val lifeSupportData = calculateLifeSupportData(diagnosticReport)
    println("Part 2 = ${lifeSupportData.lifeSupportRating}")
}