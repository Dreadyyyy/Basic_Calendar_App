package com.example.basiccalendarapp.ui.screens.month

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date

class MonthScreenViewModel : ViewModel() {
    private val _currentDate: Flow<LocalDate> = flow {
        while (true) emit(LocalDate.now())
    }
    val monthScreenUiState: StateFlow<MonthScreenUiState> =
        _currentDate.map { date: LocalDate ->
            MonthScreenUiState(
                monthName = date.month.name,
                monthLength = date.month.length(date.isLeapYear),
                currentDay = date.dayOfMonth
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MonthScreenUiState()
        )
}