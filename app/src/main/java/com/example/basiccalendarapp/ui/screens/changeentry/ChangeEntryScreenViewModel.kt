package com.example.basiccalendarapp.ui.screens.changeentry

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basiccalendarapp.data.ScheduleEntry
import com.example.basiccalendarapp.data.ScheduleRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ChangeEntryScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {
    private val id: Int = checkNotNull(savedStateHandle["id"])
    private val _changeEntry: MutableStateFlow<ChangeEntryScreenUiState> = MutableStateFlow(
        ChangeEntryScreenUiState()
    )
    val changeEntry: StateFlow<ChangeEntryScreenUiState> = _changeEntry

    init {
        viewModelScope.launch {
            _changeEntry.value = scheduleRepository.getOneScheduleEntryStream(id)
                .filterNotNull()
                .map {
                    ChangeEntryScreenUiState(it)
                }.first()
        }
    }

    fun updateEntry() = viewModelScope.launch {
        scheduleRepository.updateScheduleEntry(_changeEntry.value.scheduleEntry)
    }

    fun updateTime(newTime: Int) {
        val newScheduleEntry: ScheduleEntry = _changeEntry.value.scheduleEntry.copy(
            timeInMinutes = newTime
        )
        _changeEntry.value = _changeEntry.value.copy(
            scheduleEntry = newScheduleEntry
        )
    }

    fun updateName(newName: String) {
        val newScheduleEntry: ScheduleEntry = _changeEntry.value.scheduleEntry.copy(
            entryName = newName
        )
        _changeEntry.value = _changeEntry.value.copy(
            scheduleEntry = newScheduleEntry
        )
    }

}