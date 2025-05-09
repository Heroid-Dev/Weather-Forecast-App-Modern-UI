package com.example.weatherv1.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.DrawableRes
import com.example.weatherv1.R

@DrawableRes
fun getWeatherIconFromCondition(condition: String): Int =
    when (condition) {
        "rain" -> R.drawable.img_rain
        "snow" -> R.drawable.img_snow
        "cloudy" -> R.drawable.img_cloud
        "clear-day" -> R.drawable.img_sun
        "clear-night" -> R.drawable.img_moon
        "partly-cloudy-day" -> R.drawable.img_cloudy
        "partly-cloudy-night" -> R.drawable.img_moon_stars
        else -> R.drawable.img_sun
    }

fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

fun celsiusToFahrenheit(celsius: Double): Double {
    return (celsius * 9 / 5) + 32
}
fun kmhToMph(kmh: Double): Double {
    return kmh * 0.621371
}
fun mbToInHg(mb: Double): Double {
    return mb * 0.0295301
}
fun mmToInches(mm: Double): Double {
    return mm * 0.0393701
}