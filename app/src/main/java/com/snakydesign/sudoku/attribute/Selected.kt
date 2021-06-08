package com.snakydesign.sudoku.attribute

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.snakydesign.sudoku.SudokuCellData

object Selected : SudokuCellData.Attribute {
    @Composable
    override fun Draw() {
        Box(Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f))) {
        }
    }
}

val SudokuCellData.isSelected: Boolean get() = attributes.contains(Selected)
fun SudokuCellData.select(): SudokuCellData = copy(attributes = attributes + Selected)
fun SudokuCellData.deselect(): SudokuCellData = copy(attributes = attributes - Selected)