package com.example.weatherv1.screens.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.screens.loading.LoadingScreen
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
    val weatherState = mainViewModel.weatherStateFlow.collectAsState().value
    val context = LocalContext.current
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    val cityName = mainViewModel.cityName.collectAsState().value

    LaunchedEffect(permissionState.status) {
        if (permissionState.status.isGranted) {
            getCurrentLocation(context) { location ->
                mainViewModel.getCityNameFromApi(location.latitude, location.longitude)
                mainViewModel.getWeather("${location.latitude},${location.longitude}")
            }
        } else {
            permissionState.launchPermissionRequest()
        }
    }

//    LaunchedEffect(cityName) {
//    }

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
                cityName = cityName!!
            )
        },
        onError = {
            Text(text = weatherState.getErrorOrNull().toString())
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
