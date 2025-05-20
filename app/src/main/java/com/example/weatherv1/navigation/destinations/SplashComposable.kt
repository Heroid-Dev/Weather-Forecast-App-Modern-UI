package com.example.weatherv1.navigation.destinations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.screens.splash.SplashScreen


fun NavGraphBuilder.splashComposable(
    navigationToMainScreen:()-> Unit
) {
    composable(route=Screen.SplashScreen.name,
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(2000)
            )+fadeOut()
        }) {
        SplashScreen(navigationToMainScreen)
    }
}