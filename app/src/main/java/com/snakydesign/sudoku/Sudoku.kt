package com.snakydesign.sudoku

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SudokuView() {
    val initialTable = SudokuTable(
        Pair(2, 3) to 4,
        Pair(8, 9) to 5,
    )
    val viewModel = remember {
        SudokuViewModel(initialTable)
    }
    CompositionLocalProvider(
        LocalSudokuViewModel provides viewModel
    ) {
        Column(Modifier
            .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                viewModel.clear()
            }
            .padding(32.dp)) {

            SudokuTableView(Modifier
                .weight(2.5f)
                .align(CenterHorizontally))
            SudokuControls(
                viewModel.numberInputType().value,
                Modifier
                    .weight(1f)
                    .align(CenterHorizontally),
            ) {
                viewModel.changeNumberType(it)
            }
        }
    }
}

@Composable
private fun SudokuTableView(modifier: Modifier = Modifier) {

    val viewModel = LocalSudokuViewModel.current
    val sudokuTable = viewModel.table().collectAsState().value
    BoxWithConstraints(modifier
        .background(MaterialTheme.colors.background)
    ) {

        val width = minOf(maxWidth, maxHeight) / 9
        val height = width
        Row() {
            repeat(3) { boxRow ->
                Column() {
                    repeat(3) { boxColumn ->
                        val boxIndex = (boxColumn * 3) + boxRow
                        Box(Modifier
                            .width(width * 3)
                            .height(height * 3)) {
                            SudokuBox(boxIndex = boxIndex)
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun SudokuBox(boxIndex: Int) {
    val rowStart = boxIndex / 3
    val columnStart = boxIndex % 3
    val sudokuTable = LocalSudokuViewModel.current.table().collectAsState().value
    BoxWithConstraints(Modifier
        .fillMaxSize()
        .border(2.dp, MaterialTheme.colors.onBackground)
    ) {
        val width = maxWidth / 3
        val height = maxHeight / 3

        Row() {
            repeat(3) { repeatedColumn ->
                Column() {
                    repeat(3) { repeatedRow ->
                        val column = (columnStart * 3) + repeatedColumn
                        val row = (rowStart * 3) + repeatedRow
                        val sudokuCellValues = sudokuTable.get(row, column)
                        /*  Text("$row $column",
                              Modifier.size(100.dp),
                              fontSize = MaterialTheme.typography.h4.fontSize)*/
                        Box(Modifier.size(width, height)) {
                            SudokuCell(sudokuCellValues)
                        }
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 500, heightDp = 500)
@Composable
fun SudokuPreview() {
    SudokuView()
}

