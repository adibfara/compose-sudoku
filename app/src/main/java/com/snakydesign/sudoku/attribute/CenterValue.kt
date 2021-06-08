package com.snakydesign.sudoku.attribute

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.snakydesign.sudoku.SudokuCellData

data class CenterValue(val values: Set<Int>) : SudokuCellData.Attribute {
    @OptIn(ExperimentalUnitApi::class)
    @Composable
    override fun Draw() {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                values.sorted().joinToString(""),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center,
                fontSize = TextUnit(9f, TextUnitType.Sp),

                )
        }
    }
}

fun SudokuCellData.toggleCenterValue(value: Int): SudokuCellData {
    val currentValues =
        attributes.filterIsInstance<CenterValue>().firstOrNull()?.values.orEmpty().let {
            if (it.contains(value)) {
                it - value
            } else {
                it + value
            }
        }
    return removeCenterValues().let {
        it.copy(attributes = it.attributes + CenterValue(currentValues.toSet()))
    }
}

fun SudokuCellData.removeCenterValues(): SudokuCellData {
    return copy(attributes = attributes.filterNot { it is CenterValue }.toSet())
}