package com.example.weatherv1.navigation.destinations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.screens.about.AboutScreen

fun NavGraphBuilder.aboutComposable(navigateToMainScreen: () -> Unit) {
    composable(route=Screen.AboutScreen.name,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(1000)
            )+ fadeIn()
        }) {
        AboutScreen(navigateToMainScreen=navigateToMainScreen)
    }
}