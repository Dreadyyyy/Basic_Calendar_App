package com.example.basiccalendarapp.ui.screens.month

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basiccalendarapp.R
import com.example.basiccalendarapp.data.ScheduleEntry
import com.example.basiccalendarapp.ui.AppViewModelProvider
import com.example.basiccalendarapp.ui.theme.BasicCalendarAppTheme

@Composable
fun MonthScreen(
    monthScreenViewModel: MonthScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    padding: PaddingValues = PaddingValues(0.dp)
) {
    val monthScreenUiState: MonthScreenUiState by monthScreenViewModel.monthScreenUiState.collectAsState()
    val detailsPaneUiState: DetailsPaneUiState by monthScreenViewModel.detailsPaneUiState.collectAsState()
    val days: List<Int> = (1..monthScreenUiState.monthLength).toList()
    Scaffold(
        topBar = {
            MonthScreenTopAppBar(
                currentYear = monthScreenUiState.year,
                currentMonthName = monthScreenUiState.monthName
            )
        },
        modifier = Modifier.padding(padding)
    ) { innerPadding: PaddingValues ->
        Column {
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
                    .padding(innerPadding)
            ) {
                items(days, { day: Int -> day }) { day: Int ->
                    DayCard(
                        day = day,
                        isCurrentDay = day == monthScreenUiState.currentDay,
                        selectDay = monthScreenViewModel::changeSelectedDay,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
            monthScreenUiState.selectedDay?.let { selectedDay: Int ->
                SelectedDayDetails(
                    selectedDay = selectedDay,
                    detailsPaneUiState.scheduledTasks,
                    month = monthScreenUiState.monthName,
                    addNewScheduleEntry = {},
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1F)
                )
            } ?: Spacer(modifier = Modifier.weight(1F))
        }
    }
}

@Composable
fun DayCard(
    day: Int,
    isCurrentDay: Boolean,
    selectDay: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { selectDay(day) }
    ) {
        Text(
            text = day.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = if (isCurrentDay) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondary
                )
        )
    }
}


@Composable
fun SelectedDayDetails(
    selectedDay: Int,
    scheduledTasks: List<ScheduleEntry>,
    month: String,
    addNewScheduleEntry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(text = "$selectedDay $month")
            LazyColumn(
                modifier.weight(1F)
            ) {
                items(scheduledTasks, {scheduledTask: ScheduleEntry -> scheduledTask.id}) {scheduledTask: ScheduleEntry->
                    Text(
                        text = scheduledTask.entryName,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        FloatingActionButton(
            onClick = addNewScheduleEntry,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.add)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthScreenTopAppBar(
    currentYear: Int,
    currentMonthName: String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = "$currentMonthName $currentYear",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MonthScreenPreview() {
    BasicCalendarAppTheme {
        MonthScreen()
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 400)
@Composable
fun SelectedDayDetailsPreview() {
    BasicCalendarAppTheme {
        SelectedDayDetails(
            selectedDay = 1,
            scheduledTasks = listOf<ScheduleEntry>(),
            month = "January",
            addNewScheduleEntry = { /*TODO*/ }
        )
    }
}