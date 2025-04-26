package com.example.weatherv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherv1.navigation.WeatherNavigation
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.ui.theme.WeatherV1Theme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherV1Theme {


                        val mainViewModel = hiltViewModel<MainViewModel>()
                        WeatherNavigation(mainViewModel = mainViewModel)


            }
        }
    }
}

