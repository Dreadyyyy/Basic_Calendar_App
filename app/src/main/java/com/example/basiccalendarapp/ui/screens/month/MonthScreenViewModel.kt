package com.example.basiccalendarapp.ui.screens.month

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basiccalendarapp.data.ScheduleRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate

private const val MONTH_SCREEN_VIEW_MODEL_TAG: String = "Month Screen View Model"

class MonthScreenViewModel(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {
    private var selectedDay: Int? = null
    private val _currentDate: Flow<Pair<LocalDate, Int?>> = flow {
        while (true) {
//            Log.d(MONTH_SCREEN_VIEW_MODEL_TAG, "Date is ${monthScreenUiState.value.extractDate()}")
            emit(LocalDate.now() to selectedDay)
            delay(10L)
        }
    }
    val monthScreenUiState: StateFlow<MonthScreenUiState> =
        _currentDate.map { (date: LocalDate, selectedDay: Int?) ->
            MonthScreenUiState(
                monthName = date.month.name,
                monthLength = date.month.length(date.isLeapYear),
                currentDay = date.dayOfMonth,
                selectedDay = selectedDay
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MonthScreenUiState()
        )
    val detailsPaneUiState: StateFlow<DetailsPaneUiState> =
        scheduleRepository.getAllScheduleEntriesOfADateStream(monthScreenUiState.value.extractDate())
            .map {
                DetailsPaneUiState(it)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = DetailsPaneUiState()
            )

    fun changeSelectedDay(newSelectedDay: Int) {
        selectedDay = when (newSelectedDay) {
            selectedDay -> null
            else -> newSelectedDay
        }
    }
}

