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

data class WinningBoard (
    val sumOfUnmarkedNumbers: Int,
    val winningNumber: Int
)

data class BingoGameState(
    val remainingBoards: List<BingoBoard>,
    val numbersDrawn: Set<Int>,
    val winningBoards: List<WinningBoard>
)

fun playBingoGame(bingoGame: BingoGame): List<WinningBoard> =
    bingoGame.numbersDrawn.fold(BingoGameState(bingoGame.boards, emptySet(), emptyList())) { gameState, number ->
        val newNumbersDrawn = gameState.numbersDrawn + number
        val boardState = gameState.remainingBoards.fold(emptyList<BingoBoard>() to emptyList<BingoBoard>()) { boardStateSoFar, board ->
            if(board.rowsAndColumns.any { newNumbersDrawn.containsAll(it) }) {
                boardStateSoFar.first.plus(board) to boardStateSoFar.second
            } else {
                boardStateSoFar.first to boardStateSoFar.second.plus(board)
            }
        }
        BingoGameState(
            remainingBoards = boardState.second,
            numbersDrawn = newNumbersDrawn,
            winningBoards = gameState.winningBoards.plus(boardState.first.map { winningBoard ->
                WinningBoard(
                    sumOfUnmarkedNumbers = winningBoard.rows.flatten().filterNot { newNumbersDrawn.contains(it) }.sum(),
                    winningNumber = number
                )
            })
        )
    }.winningBoards


fun main() {
    val game = parseGame(ParseUtil.inputLines(4))

    val result = playBingoGame(game)

    val firstWin = result.first()
    println("Part 1 = ${firstWin.sumOfUnmarkedNumbers * firstWin.winningNumber}")

    val lastWin = result.last()
    println("Part 2 = ${lastWin.sumOfUnmarkedNumbers * lastWin.winningNumber}")
}