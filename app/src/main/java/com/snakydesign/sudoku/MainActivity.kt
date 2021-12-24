package com.snakydesign.sudoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.snakydesign.sudoku.ui.theme.SudokuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SudokuTheme {

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SudokuView()
                }
            }
        }
    }
}
