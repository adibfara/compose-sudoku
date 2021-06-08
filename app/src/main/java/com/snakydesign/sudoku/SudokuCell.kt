package com.snakydesign.sudoku

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.snakydesign.sudoku.attribute.isSelected

@Composable
fun SudokuCell(sudokuCellData: SudokuCellData) {
    val viewModel = LocalSudokuViewModel.current
    val color =
        animateColorAsState(targetValue = if (sudokuCellData.isSelected) MaterialTheme.colors.primaryVariant.copy(
            alpha = 0.2f) else Color.Unspecified)
    Box(Modifier
        .fillMaxSize()
        .border(1.dp, Color.Gray)
        .background(color.value)
        .clickable { viewModel.clicked(sudokuCellData) }

    ) {
        val actualText = sudokuCellData.number?.toString() ?: ""
        sudokuCellData.attributes.forEach {
            it.Draw()
        }
        Text(
            actualText,
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.h5.fontSize,

            )
    }
}
