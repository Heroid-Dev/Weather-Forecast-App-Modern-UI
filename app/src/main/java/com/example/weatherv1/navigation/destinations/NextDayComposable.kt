package com.example.weatherv1.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.screens.nextday.NextDaysScreen

fun NavGraphBuilder.nextDayComposable(
    mainViewModel: MainViewModel,
    navigateToMainScreen:()-> Unit
){
    composable(Screen.NextDayScreen.name) {
        NextDaysScreen(navigateToMainScreen)
    }
}