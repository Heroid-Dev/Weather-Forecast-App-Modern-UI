package com.example.weatherv1.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.weatherv1.R
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.utils.categorizeUVIndex
import com.example.weatherv1.utils.celsiusToFahrenheit
import com.example.weatherv1.utils.kmhToMph
import com.example.weatherv1.utils.millibarToKilopascal

data class AirQuality(
    val title: String,
    val value: String,
    @DrawableRes val icon: Int,
)


@Composable
fun AirQualityDataList(
    mainViewModel: MainViewModel,
    rainProb: Double,
    rain: Double,
    temp: Double,
    wind: Double,
    uvIndex: Double,
    humidity: Double,
    pressure: Double,
    listOfAirQuality: @Composable (List<AirQuality>) -> Unit,
) {
    val unitPref = mainViewModel.unitPrefs.collectAsState().value
    listOfAirQuality(
        listOf(
            AirQuality(
                title = "Temperature",
                value = if (unitPref.isFahrenheit)
                    "%.2f".format(celsiusToFahrenheit(temp)) + "F"
                else
                    "$temp°",
                icon = R.drawable.temp
            ),
            AirQuality(
                title = "Rain",
                value = if (unitPref.inRain_mm) "${rain.toInt()}mm" else "${rainProb.toInt()}%",
                icon = R.drawable.precip
            ),
            AirQuality(
                title = "Humidity",
                value = "%.1f".format(humidity) + "%",
                icon = R.drawable.humidity
            ),
            AirQuality(
                title = "Pressure",
                value = if (unitPref.inKPa)
                    "%.1f".format(millibarToKilopascal(pressure)) + "KPa"
                else
                    "%.1f".format(pressure) + "mbar",
                icon = R.drawable.pressure_icon
            ),
            AirQuality(
                title = "UVIndex",
                value = categorizeUVIndex(uvIndex),
                icon = R.drawable.uv_index
            ),
            AirQuality(
                title = "Wind",
                value = if (unitPref.isMph)
                    "%.1f".format(kmhToMph(wind)) + "MPH"
                else
                    "%.1f".format(wind) + "Km/h",
                icon = R.drawable.wind
            ),
        )
    )
}
