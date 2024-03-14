package com.example.basiccalendarapp.ui.screens.changeentry

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basiccalendarapp.data.getShownTime
import com.example.basiccalendarapp.ui.AppViewModelProvider

@Composable
fun ChangeEntryScreen(
    navigateBack: () -> Unit,
    changeEntryScreenViewModel: ChangeEntryScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val changeEntryScreenUiState: ChangeEntryScreenUiState by changeEntryScreenViewModel.changeEntry.collectAsState()
    BackHandler{
        changeEntryScreenViewModel.updateEntry()
        navigateBack()
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = changeEntryScreenUiState.scheduleEntry.getShownTime(),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = changeEntryScreenUiState.scheduleEntry.entryName,
            onValueChange = changeEntryScreenViewModel::updateName,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
        )
    }
}