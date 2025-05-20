package com.example.weatherv1.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.screens.search.SearchScreen

fun NavGraphBuilder.searchComposable(
    mainViewModel: MainViewModel,
    navigateToMainScreen:()-> Unit){
    composable(route=Screen.SearchScreen.name) {
        SearchScreen(
            mainViewModel = mainViewModel,
            navigateToMainScreen=navigateToMainScreen)
    }
}