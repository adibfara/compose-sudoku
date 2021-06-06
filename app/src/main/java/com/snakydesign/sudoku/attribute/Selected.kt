package com.snakydesign.sudoku.attribute

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.snakydesign.sudoku.SudokuCell

object Selected : SudokuCell.Attribute {
    @Composable
    override fun Element() {
        Box(Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f))) {
        }
    }
}

val SudokuCell.isSelected: Boolean get() = attributes.contains(Selected)
fun SudokuCell.select(): SudokuCell = copy(attributes = attributes + Selected)
fun SudokuCell.deselect(): SudokuCell = copy(attributes = attributes - Selected)