package com.example.weatherv1.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.weatherv1.navigation.Screen
import com.example.weatherv1.screens.setting.SettingScreen

fun NavGraphBuilder.settingComposable(
    navigateToMainScreen:()-> Unit
){
    composable(Screen.SettingScreen.name) {
        SettingScreen(navigateToMainScreen = navigateToMainScreen)
    }
}