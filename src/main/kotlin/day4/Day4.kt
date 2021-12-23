package day4

import util.ParseUtil

fun parseGame(inputLines: List<String>): BingoGame {
    val numbersDrawn = inputLines.first().split(",").map { it.toInt() }

    val boards = ParseUtil.parseGroups(inputLines.drop(2))
        .map { cardLines ->
            cardLines.map { cardLine ->
                cardLine.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
            }
        }.map { BingoBoard(it) }

    return BingoGame(numbersDrawn, boards)
}

data class BingoResult(
    val sumOfUnmarkedNumbers: Int,
    val winningNumber: Int
)

fun findBingo(bingoGame: BingoGame): BingoResult =
    findBingoStep(bingoGame.boards, bingoGame.numbersDrawn, emptySet())

private fun findBingoStep(boards: List<BingoBoard>, numbersLeftToDraw: List<Int>, numbersDrawn: Set<Int>): BingoResult {
    val numberDrawn = numbersLeftToDraw.first()
    val newNumbersDrawn = numbersDrawn + numberDrawn
    boards.forEach { board ->
        board.rowsAndColumns.forEach { bingoSet ->
            if (newNumbersDrawn.containsAll(bingoSet)) {
                return BingoResult(
                    sumOfUnmarkedNumbers = board.rows.flatten().filterNot { newNumbersDrawn.contains(it) }.sum(),
                    winningNumber = numberDrawn
                )
            }
        }
    }
    return findBingoStep(boards, numbersLeftToDraw.drop(1), newNumbersDrawn)
}

fun main() {
    val game = parseGame(ParseUtil.inputLines(4))

    val result = findBingo(game)
    println("Part 1 = ${result.sumOfUnmarkedNumbers * result.winningNumber}")
}