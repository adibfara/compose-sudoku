package com.snakydesign.sudoku.attribute

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.snakydesign.sudoku.SudokuCellData

object OddValue : SudokuCellData.Attribute {
    @Composable
    override fun Draw() {
        Box(Modifier
            .fillMaxSize()
            .padding(3.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colors.onSurface.copy(alpha = 0.2f))) {
        }
    }
}

fun SudokuCellData.setOdd(): SudokuCellData = copy(attributes = attributes + OddValue)
fun SudokuCellData.unsetOdd(): SudokuCellData = copy(attributes = attributes - OddValue)