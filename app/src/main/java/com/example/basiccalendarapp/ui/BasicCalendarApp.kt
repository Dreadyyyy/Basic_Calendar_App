package com.example.basiccalendarapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.basiccalendarapp.ui.navigation.NavigationGraph

@Composable
fun BasicCalendarApp(
    padding: PaddingValues
) {
    val navHostController: NavHostController = rememberNavController()
    NavigationGraph(navHostController = navHostController)
}