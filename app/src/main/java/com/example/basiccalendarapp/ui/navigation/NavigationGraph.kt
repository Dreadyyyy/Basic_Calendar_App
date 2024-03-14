package com.example.basiccalendarapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.basiccalendarapp.ui.screens.changeentry.ChangeEntryScreen
import com.example.basiccalendarapp.ui.screens.month.MonthScreen

@Composable
fun NavigationGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = CalendarScreens.MonthScreen.name
    ) {
        composable(route = CalendarScreens.MonthScreen.name) {
            MonthScreen(changeEntryDetails = { destination: String ->
                navHostController.navigate(
                    destination
                )
            })
        }
        composable(
            route = "${CalendarScreens.ChangeEntryScreen.name}/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.IntType
                }
            )
        ) {
            ChangeEntryScreen(navigateBack = navHostController::navigateUp)
        }
    }
}