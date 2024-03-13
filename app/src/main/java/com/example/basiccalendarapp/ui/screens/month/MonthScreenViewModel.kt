package com.example.basiccalendarapp.ui.screens.month

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basiccalendarapp.data.ScheduleEntry
import com.example.basiccalendarapp.data.ScheduleRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

private const val MONTH_SCREEN_VIEW_MODEL_TAG: String = "Month Screen View Model"

class MonthScreenViewModel(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {
    private var selectedDay: Int? = null
    private val _monthScreenUiState: MutableStateFlow<MonthScreenUiState> = MutableStateFlow(
        MonthScreenUiState()
    )
    val monthScreenUiState: StateFlow<MonthScreenUiState> = _monthScreenUiState.asStateFlow()

    fun getScheduledTasks(): StateFlow<List<ScheduleEntry>> {
        return scheduleRepository.getAllScheduleEntriesOfADateStream(monthScreenUiState.value.extractDate())
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf()
            )
    }

    fun changeSelectedDay(newSelectedDay: Int) {
        _monthScreenUiState.value = _monthScreenUiState.value.copy(
            selectedDay = if (newSelectedDay == selectedDay) null else newSelectedDay
        )
    }

    fun insertScheduleEntry() = viewModelScope.launch {
        scheduleRepository.insertScheduleEntry(
            ScheduleEntry(
                timeInMinutes = 0,
                date = _monthScreenUiState.value.extractDate(),
                entryName = ""
            )
        )
    }
}

