package com.example.weatherv1.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.screens.favorite.FavoriteScreen

fun NavGraphBuilder.favoriteComposable() {
    composable(Screen.FavoriteScreen.name) {
        FavoriteScreen()
    }
}