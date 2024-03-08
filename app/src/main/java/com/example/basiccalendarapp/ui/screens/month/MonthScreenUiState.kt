package com.example.basiccalendarapp.ui.screens.month

import android.health.connect.datatypes.units.Length
import java.time.LocalDate
import java.time.Month
import java.time.MonthDay

data class MonthScreenUiState(
    val monthName: String = Month.MAY.name,
    val monthLength: Int = Month.MAY.length(false),
    val currentDay: Int = 8
)
