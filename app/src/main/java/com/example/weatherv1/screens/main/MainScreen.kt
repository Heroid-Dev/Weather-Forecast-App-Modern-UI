package com.example.weatherv1.screens.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.screens.error.ErrorScreen
import com.example.weatherv1.screens.loading.LoadingScreen
import com.example.weatherv1.widgets.InfiniteColorBackground
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    navigateToSearchScreen: () -> Unit,
    navigateToNextDayScreen: () -> Unit,
    navigateToAboutScreen: () -> Unit,
    navigateToFavoriteScreen: () -> Unit,
    navigateToSettingScreen: () -> Unit,
) {
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    when {
        permissionState.status.isGranted -> {
            MainScreenContent(
                mainViewModel = mainViewModel,
                navigateToSearchScreen = navigateToSearchScreen,
                navigateToNextDayScreen = navigateToNextDayScreen,
                navigateToAboutScreen = navigateToAboutScreen,
                navigateToFavoriteScreen = navigateToFavoriteScreen,
                navigateToSettingScreen = navigateToSettingScreen
            )
        }

        else -> {
            PermissionRationaleScreen(
                onRequestPermission = { permissionState.launchPermissionRequest() }
            )
        }
    }

}

@Composable
fun PermissionRationaleScreen(onRequestPermission: () -> Unit) {
    InfiniteColorBackground(
        color1 = Color(0xFFEDFAFF),
        color2 = Color(0xFF9FD6FF),
        color3 = Color(0xFF5BBCFF),
        color4 = Color(0xFFEFF9FF)
    ) { color1, color2 ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(color1, color2),
                        start = Offset(0f, 0f),
                        end = Offset.Infinite
                    )
                )
                .padding(25.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "You need location access to display weather conditions.",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRequestPermission) {
                Text("Granting access")
            }
        }
    }
}


@Composable
fun MainScreenContent(
    mainViewModel: MainViewModel,
    navigateToSearchScreen: () -> Unit,
    navigateToNextDayScreen: () -> Unit,
    navigateToAboutScreen: () -> Unit,
    navigateToFavoriteScreen: () -> Unit,
    navigateToSettingScreen: () -> Unit,
) {
    val context = LocalContext.current
    val cityName = mainViewModel.currentCity.collectAsState().value
    val weatherState = mainViewModel.weatherStateFlow.collectAsState().value

    LaunchedEffect(cityName) {
        if (cityName==null){
            getCurrentLocation(context) { location ->
                mainViewModel.getCityNameFromApi(location.latitude, location.longitude)
            }
        }else{
            mainViewModel.getWeather(cityName.replace("(?i)\\s*county$".toRegex(), "").lowercase())
        }
    }

    weatherState.DisplayResult(
        onLoading = {
            LoadingScreen()
        },
        onSuccess = {
            val weatherInfo = weatherState.getDataOrNull()!!
            SuccessScreen(
                navigationToSearchScreen = navigateToSearchScreen,
                navigateToNextDayScreen = navigateToNextDayScreen,
                navigateToAboutScreen = navigateToAboutScreen,
                navigateToFavoriteScreen = navigateToFavoriteScreen,
                navigateToSettingScreen = navigateToSettingScreen,
                weatherInfo = weatherInfo,
                cityName = cityName!!,
                mainViewModel=mainViewModel,
            )
        },
        onError = {
            ErrorScreen(weatherState.getErrorOrNull()!!)
        }
    )
}

fun getCurrentLocation(context: Context, onLocationReceived: (Location) -> Unit) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                onLocationReceived(it)
            }
        }
    }
}
