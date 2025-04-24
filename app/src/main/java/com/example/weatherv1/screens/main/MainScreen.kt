package com.example.weatherv1.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState


@Composable
fun MainScreen(mainViewModel: MainViewModel) {

        val weatherState = mainViewModel.weatherStateFlow.collectAsState().value

        weatherState.DisplayResult(
            onLoading = {
                LoadingScreen()
            },
            onSuccess = {
                val weatherInfo=weatherState.getDataOrNull()!!
                SuccessScreen(
                    //weatherInfo=weatherInfo
                )
            },
            onError = {
                Text(text = weatherState.getErrorOrNull().toString())
            }
        )

}