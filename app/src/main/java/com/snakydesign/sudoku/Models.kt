package com.snakydesign.sudoku

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

data class SudokuTable(
    val values: List<SudokuCellData>,
) {

    fun get(row: Int, column: Int): SudokuCellData {
        return values[(row * 9) + column]
    }

    fun copy(row: Int, column: Int, sudokuCellData: SudokuCellData): SudokuTable {
        return values.toMutableList().apply {
            set((row * 9) + column, sudokuCellData)
        }.let {
            SudokuTable(it)
        }
    }

    companion object {
        @OptIn(ExperimentalStdlibApi::class)
        operator fun invoke(vararg values: Pair<Pair<Int, Int>, Int>): SudokuTable {
            return SudokuTable(buildList {
                repeat(9) { row ->
                    repeat(9) { column ->
                        val number = values.firstOrNull {
                            it.first.first == row + 1 && it.first.second == column + 1
                        }?.second
                        add(SudokuCellData(
                            number,
                            row,
                            column
                        ))
                    }
                }
            })
        }
    }
}

data class SudokuCellData(
    val number: Int?,
    val row: Int,
    val column: Int,
    val attributes: Set<Attribute> = setOf(),
) {
    interface Attribute {
        @Composable
        fun Draw()
    }
}

enum class NumberInputType {
    Actual,
    Corner,
    Center
}

internal val LocalSudokuViewModel =
    compositionLocalOf<SudokuViewModel> { SudokuViewModel(SudokuTable()) }
