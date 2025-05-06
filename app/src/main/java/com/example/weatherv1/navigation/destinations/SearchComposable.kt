package com.example.weatherv1.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.screens.search.SearchScreen

fun NavGraphBuilder.searchComposable(
    navigateToMainScreen:()-> Unit){
    composable(Screen.SearchScreen.name) {
        SearchScreen(navigateToMainScreen=navigateToMainScreen)
    }
}