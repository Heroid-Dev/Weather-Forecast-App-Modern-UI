package com.example.weatherv1.navigation

import androidx.navigation.NavHostController

class NavScreen(
    navController: NavHostController
) {
    val splashScreen:()-> Unit = {
        navController.navigate(Screen.SplashScreen.name)
    }
    val mainScreen:()-> Unit = {
        navController.navigate(Screen.MainScreen.name){

        }
    }
    val nextDayScreen:()-> Unit={
        navController.navigate(Screen.NextDayScreen.name)
    }
    val searchScreen:()-> Unit={
        navController.navigate(Screen.SearchScreen.name)
    }
    val settingScreen:()-> Unit={
        navController.navigate(Screen.SettingScreen.name)
    }
    val favoriteScreen:()-> Unit={
        navController.navigate(Screen.FavoriteScreen.name)
    }
    val aboutScreen:()-> Unit={
        navController.navigate(Screen.AboutScreen.name)
    }

}