package com.example.weatherv1.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.utils.celsiusToFahrenheit
import com.example.weatherv1.widgets.DailyForecast
import com.example.weatherv1.widgets.InfiniteColorBackground
import com.example.weatherv1.widgets.TopAppBarComponent
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun FavoriteScreen(mainViewModel: MainViewModel, navigateToMainScreen: () -> Unit) {

    mainViewModel.getAllFavorites()
    val allFavorites = mainViewModel.allFavorites.collectAsState().value
    var removeButtonState by remember { mutableStateOf(false) }

    InfiniteColorBackground(
        color1 = Color(0xFFEDFAFF),
        color2 = Color(0xFF9FD6FF),
        color3 = Color(0xFF5BBCFF),
        color4 = Color(0xFFEFF9FF)
    ) { color1, color2 ->
        Scaffold { innerPadding ->
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
                    .padding(innerPadding)
            ) {
                TopAppBarComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 22.dp, start = 10.dp, end = 10.dp),
                    title = {
                        if (removeButtonState) {
                            Spacer(modifier = Modifier.weight(.42f))
                        }
                        Text(
                            text = "Favorite weather",
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            color = Color(0xFF234195),
                            fontFamily = FontFamily.SansSerif
                        )
                        if (removeButtonState) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    },
                    navigationIcon = if (!removeButtonState) R.drawable.left_arrow else R.drawable.tick_icon,
                    onNavigationClicked = {
                        if (!removeButtonState) {
                            navigateToMainScreen()
                        } else {
                            removeButtonState = false
                        }
                    },
                    actionIcon = if (!removeButtonState) R.drawable.delete_icon else null,
                    onActionClicked = {
                        removeButtonState = true
                    },
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                FlowRow(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    maxItemsInEachRow = 2,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    allFavorites.forEach { weatherInfo ->
                        val currentTime = LocalTime.now().hour
                        val today = LocalDate.now()
                        val formattedDate = today.format(DateTimeFormatter.ISO_DATE)
                        val nowDayWeather = weatherInfo.days.first {
                            it.datetime == formattedDate
                        }
                        val nowHourlyWeather = nowDayWeather.hours.first {
                            it.datetime.substringBefore(":").toInt() == currentTime
                        }

                        DailyForecast(
                            modifier = Modifier
                                .width(160.dp)
                                .height(220.dp),
                            icon = nowHourlyWeather.icon,
                            iconSize = 75.dp,
                            temp = if (mainViewModel.unitPrefs.collectAsState().value.isFahrenheit)
                               "${celsiusToFahrenheit(nowHourlyWeather.temp).toInt()}F"
                            else
                                "${nowHourlyWeather.temp.toInt()}°",
                            title = weatherInfo.address,
                            subtitle = nowHourlyWeather.conditions,
                            removeButton = removeButtonState,
                            onClickRemoveButton = {
                                mainViewModel.deleteFromFavorites(weatherInfo.address)
                            },
                            onClickCard = {
                                mainViewModel.updateCurrentCity(weatherInfo.address)
                                navigateToMainScreen()
                            }
                        )
                    }
                }
            }
        }
    }
}


