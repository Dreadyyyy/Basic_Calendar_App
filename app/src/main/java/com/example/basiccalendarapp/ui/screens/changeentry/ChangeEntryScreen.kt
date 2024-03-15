package com.example.basiccalendarapp.ui.screens.changeentry

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basiccalendarapp.R
import com.example.basiccalendarapp.ui.AppViewModelProvider

@Composable
fun ChangeEntryScreen(
    navigateBack: () -> Unit,
    changeEntryScreenViewModel: ChangeEntryScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val changeEntryScreenUiState: ChangeEntryScreenUiState by changeEntryScreenViewModel.changeEntry.collectAsState()
    BackHandler {
        changeEntryScreenViewModel.updateEntry()
        navigateBack()
    }
    Scaffold(

    ) {innerPadding: PaddingValues->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row {
                IconButton(onClick = changeEntryScreenViewModel::increaseHours) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = stringResource(id = R.string.increase_hours)
                    )
                }
                IconButton(onClick = changeEntryScreenViewModel::increaseMinutes) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = stringResource(id = R.string.increase_minutes)
                    )
                }
            }
            Row {
                Text(text = changeEntryScreenUiState.hours)
                Text(text = changeEntryScreenUiState.minutes)
            }
            Row {
                IconButton(onClick = changeEntryScreenViewModel::decreaseHours) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = stringResource(id = R.string.decrease_hours)
                    )
                }
                IconButton(onClick = changeEntryScreenViewModel::decreaseMinutes) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = stringResource(id = R.string.decrease_minutes)
                    )
                }
            }
            OutlinedTextField(
                value = changeEntryScreenUiState.entryName,
                onValueChange = changeEntryScreenViewModel::updateName,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
            )
        }
    }
}