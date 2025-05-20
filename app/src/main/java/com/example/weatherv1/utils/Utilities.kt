package com.example.weatherv1.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.DrawableRes
import com.example.weatherv1.R
import java.time.Instant
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale

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

fun getWeatherNotificationIcon(condition: String): Int =
    when (condition) {
        "rain" -> R.drawable.rain_notif_icon
        "snow" -> R.drawable.snow_notif_icon
        "cloudy" -> R.drawable.cloudy_notif_icon
        "clear-day" -> R.drawable.sun_notif_icon
        "clear-night" -> R.drawable.moon_notif_icon
        "partly-cloudy-day" -> R.drawable.partly_cloud_notif_icon
        "partly-cloudy-night" -> R.drawable.partly_cloud_moon_notif_icon
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
fun millibarToKilopascal(millibar: Double): Double {
    return millibar * 0.1
}

fun categorizeUVIndex(uvIndex: Double): String {
    return when {
        uvIndex < 0 -> "Invalid"
        uvIndex <= 2 -> "Low"
        uvIndex <= 5 -> "Moderate"
        uvIndex <= 7 -> "High"
        uvIndex <= 10 -> "Very High"
        else -> "Extreme"
    }
}

fun getDayOfWeekFromEpoch(epochSeconds: Long): String {
    val instant = Instant.ofEpochSecond(epochSeconds)
    val zonedDateTime = instant.atZone(ZoneId.systemDefault())
    return zonedDateTime.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
}

