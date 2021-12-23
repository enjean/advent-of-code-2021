package day4

data class BingoBoard(
    val rows: List<List<Int>>
) {
    val rowsAndColumns: Set<Set<Int>>

    init {
        val rowsSets = rows.map { rowNumbers -> rowNumbers.toSet() }.toSet()
        val columnSets = rows.first().indices.map { index ->
            rows.map { row -> row[index] }.toSet()
        }.toSet()
        rowsAndColumns = rowsSets.plus(columnSets)
    }
}
