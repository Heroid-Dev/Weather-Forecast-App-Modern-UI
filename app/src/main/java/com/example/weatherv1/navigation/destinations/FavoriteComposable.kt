package com.example.weatherv1.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.screens.favorite.FavoriteScreen

fun NavGraphBuilder.favoriteComposable(
    mainViewModel: MainViewModel,
    navigateToMainScreen: () -> Unit,
) {
    composable(Screen.FavoriteScreen.name) {
        FavoriteScreen(
            mainViewModel = mainViewModel,
            navigateToMainScreen = navigateToMainScreen
        )
    }
}