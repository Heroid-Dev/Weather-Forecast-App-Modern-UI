package com.example.weatherv1.navigation.destinations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.screens.setting.SettingScreen

fun NavGraphBuilder.settingComposable(
    navigateToMainScreen: () -> Unit,
    navigationToAboutScreen:()-> Unit,
    mainViewModel: MainViewModel,
) {
    composable(route=Screen.SettingScreen.name,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(1000)
            )+ fadeIn()
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(2000)
            )+ fadeOut()
        }) {
        SettingScreen(
            navigateToMainScreen = navigateToMainScreen,
            navigationToAboutScreen = navigationToAboutScreen,
            mainViewModel = mainViewModel
        )
    }
}