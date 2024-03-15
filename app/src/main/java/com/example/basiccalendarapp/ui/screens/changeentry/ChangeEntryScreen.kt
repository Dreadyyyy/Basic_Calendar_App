package com.example.basiccalendarapp.ui.screens.changeentry

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basiccalendarapp.R
import com.example.basiccalendarapp.ui.AppViewModelProvider
import com.example.basiccalendarapp.ui.theme.BasicCalendarAppTheme

@Composable
fun ChangeEntryScreen(
    navigateBack: () -> Unit,
    changeEntryScreenViewModel: ChangeEntryScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    padding: PaddingValues
) {
    val changeEntryScreenUiState: ChangeEntryScreenUiState by changeEntryScreenViewModel.changeEntry.collectAsState()
    BackHandler {
        changeEntryScreenViewModel.updateEntry()
        navigateBack()
    }
    Scaffold(
        modifier = Modifier.padding(padding)
    ) { innerPadding: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
            ) {
                NumberChangeBlock(
                    number = changeEntryScreenUiState.hours,
                    increaseNumber = changeEntryScreenViewModel::increaseHours,
                    increaseDescriptionId = R.string.increase_hours,
                    decreaseNumber = changeEntryScreenViewModel::decreaseHours,
                    decreaseDescriptionId = R.string.decrease_hours
                )
                NumberChangeBlock(
                    number = changeEntryScreenUiState.minutes,
                    increaseNumber = changeEntryScreenViewModel::increaseMinutes,
                    increaseDescriptionId = R.string.increase_minutes,
                    decreaseNumber = changeEntryScreenViewModel::decreaseMinutes,
                    decreaseDescriptionId = R.string.decrease_minutes
                )
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

@Composable
fun NumberChangeBlock(
    number: String,
    increaseNumber: () -> Unit,
    @StringRes increaseDescriptionId: Int,
    decreaseNumber: () -> Unit,
    @StringRes decreaseDescriptionId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        IconButton(onClick = increaseNumber) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowUp,
                contentDescription = stringResource(id = increaseDescriptionId)
            )
        }
        Text(text = number)
        IconButton(onClick = decreaseNumber) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = stringResource(id = decreaseDescriptionId)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NumberChangeBlockPreview() {
    BasicCalendarAppTheme {
        NumberChangeBlock(
            number = "00",
            increaseNumber = {},
            increaseDescriptionId = R.string.increase_hours,
            decreaseNumber = {},
            decreaseDescriptionId = R.string.decrease_hours
        )
    }
}

