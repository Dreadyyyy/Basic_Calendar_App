package com.example.basiccalendarapp.ui.screens.changeentry

import com.example.basiccalendarapp.data.ScheduleEntry

data class ChangeEntryScreenUiState(
    val scheduleEntry: ScheduleEntry = ScheduleEntry(
        timeInMinutes = 0,
        date = "",
        entryName = ""
    )
)
