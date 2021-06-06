package com.snakydesign.sudoku.attribute

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.snakydesign.sudoku.SudokuCell

data class CornerValue(val values: Set<Int>) : SudokuCell.Attribute {
    @OptIn(ExperimentalUnitApi::class)
    @Composable
    override fun Element() {
        Box {
            Text(
                values.sorted().joinToString(""),
                modifier = Modifier.Companion
                    .align(Alignment.TopStart)
                    .padding(2.dp),
                fontSize = TextUnit(9f, TextUnitType.Sp),
            )
        }
    }
}

fun SudokuCell.toggleCornerValue(value: Int): SudokuCell {
    val currentValues =
        attributes.filterIsInstance<CornerValue>().firstOrNull()?.values.orEmpty().let {
            if (it.contains(value)) {
                it - value
            } else {
                it + value
            }
        }
    return removeCornerValues().let {
        it.copy(attributes = it.attributes + CornerValue(currentValues.toSet()))
    }
}

fun SudokuCell.removeCornerValues(): SudokuCell {
    return copy(attributes = attributes.filterNot { it is CornerValue }.toSet())
}