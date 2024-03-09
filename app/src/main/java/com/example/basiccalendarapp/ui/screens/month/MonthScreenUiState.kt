package com.example.basiccalendarapp.ui.screens.month

import android.health.connect.datatypes.units.Length
import java.time.LocalDate
import java.time.Month
import java.time.MonthDay

data class MonthScreenUiState(
    val monthName: String = LocalDate.now().month.name,
    val monthLength: Int = LocalDate.now().month.length(LocalDate.now().isLeapYear),
    val currentDay: Int = LocalDate.now().dayOfMonth,
    val selectedDay: Int? = null
)
