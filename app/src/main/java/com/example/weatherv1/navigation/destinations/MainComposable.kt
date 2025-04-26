package com.example.weatherv1.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.screens.main.MainScreen
import com.example.weatherv1.repositorys.MainViewModel


fun NavGraphBuilder.mainComposable(
    mainViewModel: MainViewModel
) {
    composable(Screen.MainScreen.name) {
        LaunchedEffect(true) {
            mainViewModel.getWeather(city = "Tehran")
        }
        MainScreen(mainViewModel=mainViewModel)
    }
}