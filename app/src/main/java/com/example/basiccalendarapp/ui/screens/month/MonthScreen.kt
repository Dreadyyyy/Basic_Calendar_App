package com.example.basiccalendarapp.ui.screens.month

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basiccalendarapp.ui.theme.BasicCalendarAppTheme

@Composable
fun MonthScreen(
    monthScreenViewModel: MonthScreenViewModel = viewModel(),
    padding: PaddingValues = PaddingValues(0.dp)
) {
    val monthScreenUiState: MonthScreenUiState by monthScreenViewModel.monthScreenUiState.collectAsState()
    val days: List<Int> = (1..monthScreenUiState.monthLength).toList()
    LazyVerticalGrid(columns = GridCells.Fixed(7)) {
        items(days, { day: Int -> day }) { day: Int ->
            Text(
                text = day.toString(),
                modifier = Modifier
                    .background(
                        color = if (day == monthScreenUiState.currentDay) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.secondary
                    )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MonthScreenPreview() {
    BasicCalendarAppTheme {
        MonthScreen()
    }
}