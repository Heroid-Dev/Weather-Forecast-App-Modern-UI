package com.example.weatherv1.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.example.weatherv1.R

data class AirQuality(
    val title: String,
    val value: String,
    @DrawableRes val icon: Int
)


@Composable
fun AirQualityDataList(
    rainProb: Double,
    temp: Double,
    wind: Double,
    uvIndex: Double,
    humidity: Double,
    pressure: Double,
    listOfAirQuality: @Composable (List<AirQuality>) -> Unit
) {
    listOfAirQuality(
        listOf(
            AirQuality(
                title = "Temperature",
                value = "$temp°",
                icon = R.drawable.temp
            ),
            AirQuality(
                title = "Rain",
                value = "${rainProb.toInt()}%",
                icon = R.drawable.precip
            ),
            AirQuality(
                title = "Humidity",
                value = "$humidity%",
                icon = R.drawable.humidity
            ),
            AirQuality(
                title = "Pressure",
                value = "${pressure}hPa",
                icon = R.drawable.pressure_icon
            ),
            AirQuality(
                title = "UVIndex",
                value = "$uvIndex",
                icon = R.drawable.uv_index
            ),
            AirQuality(
                title = "Wind",
                value = "${wind}km/h",
                icon = R.drawable.wind
            ),
        )
    )
}
