package com.example.weatherv1.navigation

import androidx.navigation.NavHostController

class NavScreen(
    navController: NavHostController
) {
    val splashScreen:()-> Unit = {
        navController.navigate(Screen.SplashScreen.name)
    }
    val mainScreen:()-> Unit = {
        navController.navigate(Screen.MainScreen.name)
    }
}