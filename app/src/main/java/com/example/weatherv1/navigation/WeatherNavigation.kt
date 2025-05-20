package com.example.weatherv1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.weatherv1.navigation.destinations.aboutComposable
import com.example.weatherv1.navigation.destinations.favoriteComposable
import com.example.weatherv1.navigation.destinations.mainComposable
import com.example.weatherv1.navigation.destinations.nextDayComposable
import com.example.weatherv1.navigation.destinations.searchComposable
import com.example.weatherv1.navigation.destinations.settingComposable
import com.example.weatherv1.navigation.destinations.splashComposable
import com.example.weatherv1.repositorys.MainViewModel


@Composable
fun WeatherNavigation(
    mainViewModel: MainViewModel,
) {
    val navController = rememberNavController()
    val screen = remember(navController) {
        NavScreen(navController)
    }
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.name
    ) {
        splashComposable(navigationToMainScreen = screen.mainScreen)
        mainComposable(
            mainViewModel = mainViewModel,
            navigateToSearchScreen = screen.searchScreen,
            navigateToNextDayScreen = screen.nextDayScreen,
            navigateToFavoriteScreen = screen.favoriteScreen,
            navigateToAboutScreen = screen.aboutScreen,
            navigateToSettingScreen = screen.settingScreen,
        )
        nextDayComposable(
            mainViewModel = mainViewModel,
            navigateToMainScreen = screen.mainScreen
        )
        searchComposable(
            mainViewModel = mainViewModel,
            navigateToMainScreen = screen.mainScreen
        )
        aboutComposable(navigateToMainScreen = screen.mainScreen)
        settingComposable(
            navigateToMainScreen = screen.mainScreen,
            navigationToAboutScreen = screen.aboutScreen,
            mainViewModel = mainViewModel
        )
        favoriteComposable()

    }
}