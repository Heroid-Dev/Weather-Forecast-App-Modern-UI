package com.example.weatherv1.screens.main

import android.icu.text.DateFormat
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.model.NavigationItem
import com.example.weatherv1.model.Weather
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.utils.celsiusToFahrenheit
import com.example.weatherv1.utils.getWeatherNotificationIcon
import com.example.weatherv1.utils.isInternetAvailable
import com.example.weatherv1.widgets.InfiniteColorBackground
import com.example.weatherv1.widgets.RequestState
import com.example.weatherv1.widgets.TopAppBarComponent
import com.example.weatherv1.widgets.cancelNotification
import com.example.weatherv1.widgets.showNotification
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessScreen(
    navigationToSearchScreen: () -> Unit,
    navigateToNextDayScreen: () -> Unit,
    navigateToAboutScreen: () -> Unit,
    navigateToFavoriteScreen: () -> Unit,
    navigateToSettingScreen: () -> Unit,
    weatherInfo: Weather,
    cityName: String,
    mainViewModel: MainViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var indexSelectedItem by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = DrawerDefaults.scrimColor.copy(.2f),
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.6f),
                drawerContainerColor = Color(0xF0ECF8FF),
                drawerShape = RoundedCornerShape(topEnd = 35.dp, bottomEnd = 35.dp)
            ) {
                Spacer(modifier = Modifier.padding(top = 15.dp))
                NavigationItem.entries.toTypedArray().take(4)
                    .forEachIndexed { index, navigationItem ->
                        NavigationDrawerItem(
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = Color.White
                            ),
                            label = {
                                Text(
                                    text = navigationItem.title,
                                    fontWeight = if (indexSelectedItem == index) FontWeight.Bold else FontWeight.Normal,
                                    color = if (indexSelectedItem == index) Color(0xFF3D6AFF) else Color.Black
                                )
                            },
                            selected = indexSelectedItem == index,
                            onClick = {
                                when (navigationItem) {
                                    NavigationItem.About -> navigateToAboutScreen()
                                    NavigationItem.Favorites -> navigateToFavoriteScreen()
                                    NavigationItem.Setting -> navigateToSettingScreen()
                                    else -> {}
                                }
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(navigationItem.icon),
                                    contentDescription = navigationItem.title,
                                    tint = if (indexSelectedItem == index) Color(0xFF3D6AFF) else Color.Black
                                )
                            }
                        )
                    }
            }
        }
    ) {

        SuccessScreenContent(
            cityName = cityName,
            weatherInfo=weatherInfo,
            onSearchClicked = navigationToSearchScreen,
            onNextDayClicked = navigateToNextDayScreen,
            onMenuClicked = {
                scope.launch {
                    drawerState.open()
                }
            },
            mainViewModel = mainViewModel
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SuccessScreenContent(
    cityName: String,
    weatherInfo: Weather,
    onSearchClicked: () -> Unit,
    onMenuClicked: () -> Unit,
    onNextDayClicked: () -> Unit,
    mainViewModel: MainViewModel
) {

    val refreshState = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context= LocalContext.current

    val currentTime= LocalTime.now().hour
    val today= LocalDate.now()
    val formattedDate=today.format(DateTimeFormatter.ISO_DATE)

    val nowDayWeather= weatherInfo.days.first {
        it.datetime == formattedDate
    }
    val nowHourlyWeather =nowDayWeather.hours.first {
        it.datetime.substringBefore(":").toInt() == currentTime
    }
    val tempMin=if (mainViewModel.unitPrefs.collectAsState().value.isFahrenheit)
        "${celsiusToFahrenheit(nowDayWeather.tempmin).toInt()}F"
    else
        "${nowDayWeather.tempmin.toInt()}°"
    val tempMax=if (mainViewModel.unitPrefs.collectAsState().value.isFahrenheit)
        "${celsiusToFahrenheit(nowDayWeather.tempmax)}F"
    else
        "${nowDayWeather.tempmax.toInt()}°"
    val descriptionNews="${nowDayWeather.description}Highs $tempMax and lows $tempMin."
    val notificationEnabled=mainViewModel.notificationEnabled.collectAsState().value
    if (notificationEnabled){
        showNotification(context,"${nowHourlyWeather.conditions} in $cityName",descriptionNews,getWeatherNotificationIcon(nowHourlyWeather.icon))
    }else{
        cancelNotification(context)
    }
    InfiniteColorBackground(
        color1 = Color(0xFFEDFAFF),
        color2 = Color(0xFF9FD6FF),
        color3 = Color(0xFF5BBCFF),
        color4 = Color(0xFFEFF9FF)
    ) { color1, color2 ->
        Scaffold { innerPadding ->
            PullToRefreshBox(
                isRefreshing = isRefreshing,
                state = refreshState,
                onRefresh = {
                    if (!isInternetAvailable(context)) {
                        Toast.makeText(context, "No internet connection.", Toast.LENGTH_SHORT).show()

                    }else {
                        isRefreshing = true
                        mainViewModel.getWeather(city = weatherInfo.address, forceRefresh = true,isLoading=true)
                        mainViewModel.weatherStateFlow
                            .onEach {
                                if (it !is RequestState.Loading) {
                                    isRefreshing = false
                                }
                            }
                            .launchIn(scope)
                    }
                }
            ) {
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
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .verticalScroll(state = rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TopAppBarComponent(
                            title = {
                                LocationInfoText(city = cityName, localTime = nowHourlyWeather.datetimeEpoch.toLong())
                            },
                            navigationIcon = R.drawable.menuicon,
                            actionIcon = R.drawable.searchicon,
                            onNavigationClicked = onMenuClicked,
                            onActionClicked = onSearchClicked,
                            horizontalArrangement = Arrangement.SpaceBetween
                        )
                        DailyForecast(
                            mainViewModel= mainViewModel,
                            nowWeather = nowHourlyWeather
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color(0x28FFFFFF), shape =
                                        RoundedCornerShape(10.dp)
                                )
                                .padding(5.dp)
                                .basicMarquee(
                                    iterations = 1000,
                                    repeatDelayMillis = 1000,
                                ),
                            text = "$descriptionNews    ".repeat(2),
                            style = MaterialTheme.typography.labelMedium,
                            color = Color(0xFF234195),
                            maxLines = 1,
                            overflow = TextOverflow.Visible
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        AirQualityBox(
                            modifier = Modifier.padding(horizontal = 40.dp),
                            nowWeather=nowHourlyWeather,
                            mainViewModel=mainViewModel
                        )

                        HeaderHourlyForecast(
                            modifier = Modifier.padding(top = 8.dp),
                            onHeaderForecastClicked = onNextDayClicked
                        )
                    }
                    HourlyForecast(listOfHour=nowDayWeather.hours.filter {
                        it.datetime.substringBefore(":").toInt() >= currentTime
                    },mainViewModel=mainViewModel)
                }
            }
        }
    }
}


@Composable
fun LocationInfoText(
    city: String,
    localTime: Long,
) {
    val dateFormat = DateFormat.getDateInstance(DateFormat.ERA_FIELD)
    val formatted = dateFormat.format(Date(localTime * 1000))

    Column(
        modifier = Modifier.padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.location),
                contentDescription = stringResource(R.string.location_icon),
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = city,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = Color(0xFF234195),
                fontFamily = FontFamily.SansSerif
            )
        }
        Text(
            text = formatted,
            fontSize = MaterialTheme.typography.labelMedium.fontSize,
            color = Color(0xFF234195).copy(alpha = .7f)
        )
    }
}

