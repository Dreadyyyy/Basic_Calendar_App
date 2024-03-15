package com.example.basiccalendarapp.ui.screens.changeentry

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basiccalendarapp.data.ScheduleEntry
import com.example.basiccalendarapp.data.ScheduleRepository
import com.example.basiccalendarapp.data.toChangeEntryScreenUiState
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
    private var hoursAmount: Int = 0
    private var minutesAmount: Int = 0
    private val _changeEntry: MutableStateFlow<ChangeEntryScreenUiState> = MutableStateFlow(
        ChangeEntryScreenUiState()
    )
    val changeEntry: StateFlow<ChangeEntryScreenUiState> = _changeEntry

    init {
        viewModelScope.launch {
            _changeEntry.value = scheduleRepository.getOneScheduleEntryStream(id)
                .filterNotNull()
                .map {
                    it.toChangeEntryScreenUiState()
                }.first()
        }
    }

    fun updateEntry() = viewModelScope.launch {
        scheduleRepository.updateScheduleEntry(_changeEntry.value.toScheduleEntry())
    }

    fun increaseHours() {
        hoursAmount++
        hoursAmount %= 24
        updateTime()
    }
    fun decreaseHours() {
        hoursAmount--
        if (hoursAmount < 0) hoursAmount = 23
        hoursAmount %= 24
        updateTime()
    }
    fun increaseMinutes() {
        minutesAmount++
        minutesAmount %= 24
        updateTime()
    }
    fun decreaseMinutes() {
        minutesAmount--
        if (minutesAmount < 0) minutesAmount = 23
        minutesAmount %= 24
        updateTime()
    }

    fun updateName(newName: String) {
        _changeEntry.value = _changeEntry.value.copy(
            entryName = newName
        )
    }
    private fun updateTime() {
        var newHours: String = "$hoursAmount"
        if (newHours.length < 2) newHours = "0$newHours"
        var newMinutes: String = "$minutesAmount"
        if (newMinutes.length < 2) newMinutes =  "0$newMinutes"
        _changeEntry.value = _changeEntry.value.copy(
            hours = newHours,
            minutes = newMinutes
        )
    }

    private fun ChangeEntryScreenUiState.toScheduleEntry(): ScheduleEntry {
        return ScheduleEntry(
            id = id,
            timeInMinutes = hoursAmount * 60 + minutesAmount,
            date = date,
            entryName = entryName
        )
    }
}