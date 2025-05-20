package com.example.weatherv1.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.screens.about.AboutScreen

fun NavGraphBuilder.aboutComposable(navigateToMainScreen: () -> Unit) {
    composable(Screen.AboutScreen.name) {
        AboutScreen(navigateToMainScreen=navigateToMainScreen)
    }
}