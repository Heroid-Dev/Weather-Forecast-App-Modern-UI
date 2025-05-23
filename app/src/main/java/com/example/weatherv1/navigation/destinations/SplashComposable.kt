package com.example.weatherv1.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.screens.splash.SplashScreen


fun NavGraphBuilder.splashComposable(
    navigationToMainScreen:()-> Unit
) {
    composable(route=Screen.SplashScreen.name) {
        SplashScreen(navigationToMainScreen)
    }
}