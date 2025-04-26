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
    val nextDayScreen:()-> Unit={
        navController.navigate(Screen.NextDayScreen.name)
    }
    val searchScreen:()-> Unit={
        navController.navigate(Screen.SearchScreen.name)
    }
}