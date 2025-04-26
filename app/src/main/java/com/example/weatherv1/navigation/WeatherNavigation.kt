package com.example.weatherv1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.weatherv1.navigation.destinations.mainComposable
import com.example.weatherv1.navigation.destinations.nextDayComposable
import com.example.weatherv1.navigation.destinations.searchComposable
import com.example.weatherv1.navigation.destinations.splashComposable
import com.example.weatherv1.repositorys.MainViewModel


@Composable
fun WeatherNavigation(
    mainViewModel: MainViewModel
){
    val navController= rememberNavController()
    val screen= remember(navController){
        NavScreen(navController)
    }
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.name
    ) {
        splashComposable(navigationToMainScreen = screen.mainScreen)
        mainComposable(mainViewModel = mainViewModel)
        nextDayComposable(mainViewModel=mainViewModel,navigateToMainScreen = screen.nextDayScreen)
        searchComposable(navigateToMainScreen = screen.mainScreen)
    }
}