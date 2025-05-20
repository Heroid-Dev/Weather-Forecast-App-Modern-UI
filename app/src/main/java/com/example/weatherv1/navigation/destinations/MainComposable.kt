package com.example.weatherv1.navigation.destinations

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.screens.main.MainScreen


fun NavGraphBuilder.mainComposable(
    mainViewModel: MainViewModel,
    navigateToSearchScreen: () -> Unit,
    navigateToNextDayScreen: () -> Unit,
    navigateToAboutScreen: () -> Unit,
    navigateToFavoriteScreen: () -> Unit,
    navigateToSettingScreen: () -> Unit,
) {
    composable(route=Screen.MainScreen.name,
        enterTransition = {
            fadeIn(animationSpec = tween(1000))
        }) {
        MainScreen(
            mainViewModel = mainViewModel,
            navigateToSearchScreen = navigateToSearchScreen,
            navigateToNextDayScreen = navigateToNextDayScreen,
            navigateToAboutScreen = navigateToAboutScreen,
            navigateToFavoriteScreen = navigateToFavoriteScreen,
            navigateToSettingScreen = navigateToSettingScreen,
        )
    }
}