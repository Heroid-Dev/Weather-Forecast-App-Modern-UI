package com.example.weatherv1.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.screens.splash.SplashScreen


fun NavGraphBuilder.splashComposable(
    navigationToMainScreen:()-> Unit
) {
    composable(Screen.SplashScreen.name) {
        SplashScreen(navigationToMainScreen)
    }
}