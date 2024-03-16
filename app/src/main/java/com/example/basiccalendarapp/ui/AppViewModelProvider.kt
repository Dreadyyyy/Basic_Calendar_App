package com.example.basiccalendarapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.basiccalendarapp.BasicCalendarApplication
import com.example.basiccalendarapp.ui.screens.changeentry.ChangeEntryScreen
import com.example.basiccalendarapp.ui.screens.changeentry.ChangeEntryScreenViewModel
import com.example.basiccalendarapp.ui.screens.month.MonthScreenViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MonthScreenViewModel(
                basicCalendarApplication().container.scheduleRepository,
                basicCalendarApplication().container.alarmScheduler
            )
        }
        initializer {
            ChangeEntryScreenViewModel(
                this.createSavedStateHandle(),
                basicCalendarApplication().container.scheduleRepository
            )
        }
    }
}

fun CreationExtras.basicCalendarApplication(): BasicCalendarApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BasicCalendarApplication)
