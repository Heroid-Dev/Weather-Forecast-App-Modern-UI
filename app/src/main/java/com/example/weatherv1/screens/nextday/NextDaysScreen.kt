package com.example.weatherv1.screens.nextday

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.utils.celsiusToFahrenheit
import com.example.weatherv1.utils.customShadow
import com.example.weatherv1.utils.getWeatherIconFromCondition
import com.example.weatherv1.utils.kmhToMph
import com.example.weatherv1.widgets.InfiniteColorBackground
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun NextDaysScreen2(
    navigateToMainScreen: () -> Unit,
    mainViewModel: MainViewModel,
) {
    val weahterState = mainViewModel.weatherStateFlow.collectAsState().value
    val days = weahterState.getDataOrNull()?.days!!
    val today = LocalDate.now().plusDays(1)
    val unitPref = mainViewModel.unitPrefs.collectAsState().value
    val formattedDate = today.format(DateTimeFormatter.ISO_DATE)
    val tomorrowDayInfo = days.first {
        it.datetime == formattedDate
    }

    InfiniteColorBackground(
        color1 = Color(0xFFEDFAFF),
        color2 = Color(0xFF9FD6FF),
        color3 = Color(0xFF5BBCFF),
        color4 = Color(0xFFEFF9FF)
    //        color1 = Color(0xFF5BBCFF),
    //        color2 = Color(0xFF686CFF),
    //        color3 = Color(0xFF686CFF),
    //        color4 = Color(0xFF5BBCFF)
    ) { color1, color2 ->
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(color1, color2),
                            start = Offset(0f, 0f),
                            end = Offset.Infinite
                        )
                    )
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TopBarNextDaysScreen(
                    onBackClicked = navigateToMainScreen
                )
                Box(
                    modifier = Modifier
                        .padding(top = 15.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                        .fillMaxWidth()
                        .height(150.dp)
                        .customShadow(
                            color = Color(0xFF7598D2),
                            alpha = .3f,
                            borderRadius = 32.dp,
                            shadowRadius = 5.dp,
                            offsetY = 6.dp,
                            offsetX = 1.dp
                        )
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.White.copy(alpha = 0.95f),
                                    Color.White.copy(alpha = 0.05f)
                                )
                            ),
                            shape = RoundedCornerShape(32.dp)
                        )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,

                    ) {
                        Image(
                            painter = painterResource(
                                id = getWeatherIconFromCondition(
                                    tomorrowDayInfo.icon
                                )
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .height(164.dp)
                                .weight(2f)
                        )
                        Column(
                            modifier = Modifier
                                .weight(2f)
                        ) {
                            Text(
                                text = "Tomorrow",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF204B6E)
                            )
                            Text(
                                text = "${
                                    if (unitPref.isFahrenheit)
                                        celsiusToFahrenheit(tomorrowDayInfo.tempmax).toInt()
                                            .toString() + "F" else "${tomorrowDayInfo.tempmax.toInt()}°"
                                }/${
                                    if (unitPref.isFahrenheit) celsiusToFahrenheit(tomorrowDayInfo.tempmin).toInt()
                                        .toString() + "F" else "${tomorrowDayInfo.tempmin.toInt()}°"
                                }",
                                style = MaterialTheme.typography.displaySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF204B6E)
                            )
                            Text(
                                text = tomorrowDayInfo.conditions,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.W300,
                                color = Color(0xFF204B6E)
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(top = 1.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                        .fillMaxWidth()
                        .height(100.dp)
                        .customShadow(
                            color = Color(0xFF7598D2),
                            alpha = .3f,
                            borderRadius = 70.dp,
                            shadowRadius = 5.dp,
                            offsetY = 6.dp,
                            offsetX = 1.dp
                        )
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.White.copy(alpha = 0.95f),
                                    Color.White.copy(alpha = 0.05f)
                                )
                            ),
                            shape = RoundedCornerShape(70.dp)
                        )
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.humidity),
                                contentDescription = "humidity icon"
                            )
                            Text(text = "${tomorrowDayInfo.humidity.toInt()}%")
                            Text(text = "Humidity",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF204B6E))
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.precip),
                                contentDescription = "Rain icon"
                            )
                            Text(text = if (unitPref.inRain_mm) "${tomorrowDayInfo.precip.toInt()}mm" else "${tomorrowDayInfo.precipprob.toInt()}%")
                            Text(text = "Rain",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF204B6E))
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.wind),
                                contentDescription = null
                            )
                            Text(text = if (unitPref.isMph)
                        "%.1f".format(kmhToMph(tomorrowDayInfo.windspeed)) + "MPH"
                    else
                        "%.1f".format(tomorrowDayInfo.windspeed) + "Km/h")
                            Text(text = "Wind speed",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF204B6E))
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxSize()
                        .background(
                            color = Color.White.copy(.5f),
                            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                        )
                ) {
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text(
                            modifier = Modifier.padding(start = 20.dp, top = 35.dp),
                            text = "EveryDays",
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                        RowsOfDaysOfTheWeek(days = days, mainViewModel = mainViewModel)
                    }
                }
            }
        }
    }
}

