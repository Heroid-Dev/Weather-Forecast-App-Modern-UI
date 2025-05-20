package com.example.weatherv1.screens.search

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.utils.celsiusToFahrenheit
import com.example.weatherv1.utils.getWeatherIconFromCondition
import com.example.weatherv1.utils.isInternetAvailable
import com.example.weatherv1.utils.kmhToMph
import com.example.weatherv1.widgets.BelowBackground
import com.example.weatherv1.widgets.InfiniteColorBackground
import com.example.weatherv1.widgets.RequestState
import com.example.weatherv1.widgets.WeatherCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    mainViewModel: MainViewModel,
    navigateToMainScreen: () -> Unit = {},
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val unitPref = mainViewModel.unitPrefs.collectAsState().value
    val weatherState = mainViewModel.weatherStateFlow.collectAsState().value
    var isLoading by remember { mutableStateOf(false) }

    var searchText by remember { mutableStateOf("") }
    val searchTriggered = remember { mutableStateOf(false) }
    val transitionCard = updateTransition(
        targetState = searchTriggered.value
    )
    val textFieldOffsetY by transitionCard.animateDp(
        targetValueByState = {
            if (it) (-100).dp else 0.dp
        },
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        }
    )
    val belowBack by transitionCard.animateFloat(
        targetValueByState = {
            if (it) .90f else .5f
        },
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
        }
    )
    InfiniteColorBackground(
        color1 = Color(0xFFEDFAFF),
        color2 = Color(0xFF9FD6FF),
        color3 = Color(0xFF5BBCFF),
        color4 = Color(0xFFEFF9FF)
//        color1 = Color(0xFF5BBCFF),
//        color2 = Color(0xFF686CFF),
//        color3 = Color(0xFF686CFF),
//        color4 = Color(0xFF5BBCFF),
    ) { color1, color2 ->

        BelowBackground(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(belowBack)
                .clip(RoundedCornerShape(bottomStart = 35.dp, bottomEnd = 35.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(color1, color2),
                        start = Offset(0f, 0f),
                        end = Offset.Infinite
                    )
                )
        ) {
            TopBarSearchScreen(
                onClickBackButton = navigateToMainScreen
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SearchTextField(
                    modifier = Modifier.offset(y = textFieldOffsetY),
                    searchText = searchText,
                    onValueChange = {
                        searchText = it
                        if (it.isEmpty()) {
                            searchTriggered.value = false
                        }
                    },
                    onSearch = {
                        if (!isInternetAvailable(context)) {
                            Toast.makeText(context, "No internet connection.", Toast.LENGTH_SHORT)
                                .show()
                            return@SearchTextField
                        }
                        isLoading = true
                        mainViewModel.getWeather(city = searchText, forceRefresh = true)
                        scope.launch {
                            delay(2000)
                            mainViewModel.weatherStateFlow.collectLatest { state ->
                                when (state) {
                                    is RequestState.Success -> {
                                        isLoading = false
                                        searchTriggered.value = true
                                    }

                                    is RequestState.Error -> {
                                        isLoading = false
                                        Toast.makeText(
                                            context,
                                            "Error receiving information.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        searchTriggered.value = false
                                    }

                                    else -> {}
                                }
                            }
                        }
                    },

                    )
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(top = 26.dp),
                        color = Color(0xFF495D92),

                    )
                }
                AnimatedVisibility(
                    visible = searchTriggered.value && weatherState is RequestState.Success
                ) {
                    val weather = weatherState.getDataOrNull()
                    weather?.let {
                        WeatherCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
                                .animateEnterExit(
                                    enter = slideInVertically(
                                        animationSpec = tween(1000),
                                        initialOffsetY = { it }) + fadeIn(),
                                    exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
                                ),
                            onCardClicked = {},
                            weatherImage = getWeatherIconFromCondition(it.days.first().icon),
                            time = "Today",
                            maxTemp = if (unitPref.isFahrenheit)
                                celsiusToFahrenheit(it.days.first().tempmax).toInt()
                                    .toString() + "F"
                            else
                                "${it.days.first().tempmax.toInt()}°",
                            minTemp = if (unitPref.isFahrenheit)
                                celsiusToFahrenheit(it.days.first().tempmin).toInt()
                                    .toString() + "F"
                            else
                                "${it.days.first().tempmin.toInt()}°",
                            status = it.days.first().conditions,
                            humidity = it.days.first().humidity.toInt(),
                            Precipitation = if (unitPref.inRain_mm) "${it.days.first().precip.toInt()}mm" else "${it.days.first().precipprob.toInt()}%",
                            Wind =  if (unitPref.isMph)
                                "%.1f".format(kmhToMph(it.days.first().windspeed)) + "MPH"
                            else
                                "%.1f".format(it.days.first().windspeed) + "Km/h"
                        )

                    }
                }
                AnimatedVisibility(
                    visible = searchTriggered.value && weatherState is RequestState.Success
                ) {
                    Button(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .animateEnterExit(
                                enter = slideInHorizontally(
                                    animationSpec = tween(2000),
                                    initialOffsetX = { it }) + fadeIn(),
                                exit = slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
                            ), onClick = {
                            val weather = weatherState.getDataOrNull()
                            weather?.let {
                                mainViewModel.updateCurrentCity(it.address)
                                navigateToMainScreen()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF495D92)
                        )
                    ) {
                        Text(
                            text = "Set to default city",
                            color = Color.White
                        )
                    }
                }

            }
        }
    }
}



