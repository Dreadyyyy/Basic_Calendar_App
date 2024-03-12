package com.example.basiccalendarapp.ui.screens.month

import com.example.basiccalendarapp.data.ScheduleEntry
import java.time.LocalDate

data class MonthScreenUiState(
    val year: Int = LocalDate.now().year,
    val monthName: String = LocalDate.now().month.name,
    val monthLength: Int = LocalDate.now().month.length(LocalDate.now().isLeapYear),
    val currentDay: Int = LocalDate.now().dayOfMonth,
    val selectedDay: Int? = null
)

data class DetailsPaneUiState(
    val scheduledTasks: List<ScheduleEntry> = listOf()
)

fun MonthScreenUiState.extractDate(): String {
    return "$year-$monthName-$selectedDay"
}
