package com.snakydesign.sudoku

import com.snakydesign.sudoku.attribute.CenterValue
import com.snakydesign.sudoku.attribute.CornerValue
import com.snakydesign.sudoku.attribute.Selected
import org.junit.Test

class SudokuViewModelTest {

    private val sudokuCellData = SudokuCellData(null, 0, 0, setOf(CornerValue(setOf(3, 4))))
    private val sudokuCellData1 = SudokuCellData(null, 0, 1, setOf(CenterValue(setOf(1, 2))))
    private val sudokuCellData2 = SudokuCellData(null, 0, 2, setOf(Selected))
    private val sudokuTable = SudokuTable(listOf(sudokuCellData, sudokuCellData1, sudokuCellData2))
    private fun createViewModel() = SudokuViewModel(sudokuTable)

    @Test
    fun `when view model is created, state is equal to the given state`() {
        with(createViewModel()) {
            assert(table().value == sudokuTable)
        }
    }

    @Test
    fun `when a cell is clicked, the cell is updated in the table`() {
        with(createViewModel()) {
            clicked(sudokuCellData)
            assert(table().value.values.contains(sudokuCellData.copy(attributes = sudokuCellData.attributes + Selected)))
            clicked(sudokuCellData)
            assert(!table().value.values.contains(sudokuCellData.copy(attributes = sudokuCellData.attributes + Selected)))
        }
    }

    @Test
    fun `when a number is pressed, the selected cell's values get updated`() {
        with(createViewModel()) {
            clicked(sudokuCellData)
            onNumberPressed(2)
            assert(table().value.values.filter { it.number == 2 }.map { Pair(it.row, it.column) }
                .toSet() == setOf(
                Pair(0, 0), Pair(0, 2)
            ))
        }
    }

    @Test
    fun `when a number is pressed and current type is center, the cell's center values are updated`() {
        with(createViewModel()) {
            clicked(sudokuCellData)
            clicked(sudokuCellData1)
            changeNumberType(NumberInputType.Center)
            onNumberPressed(2)
            onNumberPressed(3)
            val firstCell = table().value.values.first {
                it.row == 0 && it.column == 0
            }
            with(firstCell) {
                assert(attributes.filterIsInstance<CenterValue>().first().values.toSet() == setOf(2,
                    3))
            }

            val secondCell = table().value.values.first {
                it.row == 0 && it.column == 1
            }
            with(secondCell) {
                assert(attributes.filterIsInstance<CenterValue>().first().values.toSet() == setOf(1,
                    3))
            }
        }
    }

    @Test
    fun `when a number is pressed and current type is corner, the cell's corner values are updated`() {
        with(createViewModel()) {
            clicked(sudokuCellData)
            clicked(sudokuCellData1)
            changeNumberType(NumberInputType.Corner)
            onNumberPressed(2)
            onNumberPressed(3)
            val firstCell = table().value.values.first {
                it.row == 0 && it.column == 0
            }
            with(firstCell) {
                assert(attributes.filterIsInstance<CornerValue>().first().values.toSet() == setOf(2,
                    4))
            }

            val secondCell = table().value.values.first {
                it.row == 0 && it.column == 1
            }
            with(secondCell) {
                assert(attributes.filterIsInstance<CornerValue>().first().values.toSet() == setOf(2,
                    3))
            }
        }
    }

    @Test
    fun `clear() deselects everything`() {
        with(createViewModel()) {
            clicked(sudokuCellData)
            clicked(sudokuCellData1)
            assert(table().value.values.any { it.attributes.contains(Selected) })
            clear()
            assert(table().value.values.none { it.attributes.contains(Selected) })
        }
    }
}