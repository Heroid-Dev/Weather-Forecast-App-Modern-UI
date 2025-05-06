package com.example.weatherv1.screens.main

import android.icu.text.DateFormat
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.model.NavigationItem
import com.example.weatherv1.model.Weather
import com.example.weatherv1.widgets.InfiniteColorBackground
import com.example.weatherv1.widgets.TopAppBarComponent
import kotlinx.coroutines.launch
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessScreen(
    //weatherInfo: Weather,
    navigationToSearchScreen: () -> Unit,
    navigateToNextDayScreen: () -> Unit,
    navigateToAboutScreen: () -> Unit,
    navigateToFavoriteScreen: () -> Unit,
    navigateToSettingScreen: () -> Unit,
    weatherInfo: Weather,
    cityName: String,
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
            onSearchClicked = navigationToSearchScreen,
            onNextDayClicked = navigateToNextDayScreen,
            onMenuClicked = {
                scope.launch {
                    drawerState.open()
                }
            }
        )
    }

}

@Composable
private fun SuccessScreenContent(
    cityName: String,
    onSearchClicked: () -> Unit,
    onMenuClicked: () -> Unit,
    onNextDayClicked: () -> Unit,
) {
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
                Column(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TopAppBarComponent(
                        title = {
                            LocationInfoText(city = cityName, localTime = 1744041917)
                        },
                        navigationIcon = R.drawable.menuicon,
                        actionIcon = R.drawable.searchicon,
                        onNavigationClicked = onMenuClicked,
                        onActionClicked = onSearchClicked,
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    DailyForecast(
                        //weatherInfo = weatherInfo
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier
                            .background(
                                color = Color(0x28FFFFFF), shape =
                                    RoundedCornerShape(10.dp)
                            )
                            .padding(5.dp)
                            .basicMarquee(
                                iterations = 1000,
                                repeatDelayMillis = 1000,

                                ),
                        text = "Similar temperatures continuing with a chance of rain tomorrow & Wednesday.",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF234195)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    AirQualityBox(
                        modifier = Modifier.padding(horizontal = 40.dp)
                        //weatherInfo = weatherInfo
                    )
                    HeaderHourlyForecast(
                        modifier = Modifier.padding(top = 8.dp),
                        onHeaderForecastClicked = onNextDayClicked
                    )
                }
                HourlyForecast()
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

//@Preview
//@Composable
//private fun SuccessScreenPreview() {
//    SuccessScreen(
//        navigationToSearchScreen = {},
//        navigateToNextDayScreen = {},
//        weatherInfo = Weather,
//        cityName = ""
//    )
//}