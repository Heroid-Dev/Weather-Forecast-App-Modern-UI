package com.example.weatherv1.utils

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