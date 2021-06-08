package com.snakydesign.sudoku

import com.snakydesign.sudoku.attribute.deselect
import com.snakydesign.sudoku.attribute.isSelected
import com.snakydesign.sudoku.attribute.select
import com.snakydesign.sudoku.attribute.toggleCenterValue
import com.snakydesign.sudoku.attribute.toggleCornerValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SudokuViewModel(private val sudokuTable: SudokuTable) {

    private val _table = MutableStateFlow(sudokuTable)
    private val _numberInputType = MutableStateFlow<NumberInputType>(NumberInputType.Actual)
    fun table(): StateFlow<SudokuTable> = _table
    fun numberInputType(): StateFlow<NumberInputType> = _numberInputType

    private fun updateCell(
        sudokuCellData: SudokuCellData,
        newSudokuCellData: SudokuCellData,
    ) {
        val state = _table.value
        val newState =
            state.copy(sudokuCellData.row, sudokuCellData.column, newSudokuCellData)
        _table.value = newState
    }

    fun clicked(sudokuCellData: SudokuCellData) {
        val newSudokuCell = if (sudokuCellData.isSelected) {
            sudokuCellData.deselect()
        } else {
            sudokuCellData.select()
        }
        updateCell(sudokuCellData, newSudokuCell)
    }

    fun onNumberPressed(number: Int) {
        val newNumbers =
            _table.value.values.map {
                if (it.isSelected) {
                    when (_numberInputType.value) {
                        NumberInputType.Actual -> {
                            it.copy(number = number)
                        }
                        NumberInputType.Corner -> {
                            it.toggleCornerValue(number)
                        }
                        NumberInputType.Center -> {
                            it.toggleCenterValue(number)
                        }
                    }
                } else {
                    it
                }
            }
        _table.value = _table.value.copy(newNumbers)
    }

    fun changeNumberType(numberInputType: NumberInputType) {
        _numberInputType.value = numberInputType
    }

    fun clear() {
        val newNumbers =
            _table.value.values.map { it.deselect() }
        _table.value = _table.value.copy(newNumbers)
    }

    fun delete() {
    }
}
