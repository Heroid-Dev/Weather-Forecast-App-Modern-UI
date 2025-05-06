package com.example.weatherv1.navigation.destinations

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
    composable(Screen.MainScreen.name) {
//        LaunchedEffect(true) {
//            mainViewModel.getWeather(city = "Tehran")
//        }

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