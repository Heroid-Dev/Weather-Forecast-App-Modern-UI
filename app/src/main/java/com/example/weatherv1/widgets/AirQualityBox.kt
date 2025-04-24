package com.example.weatherv1.widgets

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherv1.model.AirQualityDataList
import com.example.weatherv1.ui.theme.ColorSurface
import com.example.weatherv1.utils.customShadow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AirQualityBox(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .customShadow(
                color = Color(0xFF2B8DE5),
                alpha = .6f,
                borderRadius = 32.dp,
                shadowRadius = 10.dp,
                offsetY = 10.dp,
                offsetX = 1.dp
            ),
        shape = RoundedCornerShape(32.dp),
        color = ColorSurface
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp, horizontal = 28.dp),
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(35.dp)
        ) {
            AirQualityItems()
        }

    }
}

@Composable
fun AirQualityItems() {
    AirQualityDataList(
        rainProb = 51.6,
        temp = 17.2,
        wind = 25.2,
        uvIndex = 9.0,
        humidity = 49.3,
        pressure = 1007.5
    ) { listOfAirQuality ->
        listOfAirQuality.forEach { airQuality ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Image(
                    painter = painterResource(airQuality.icon),
                    contentDescription = airQuality.title,
                    modifier = Modifier.size(30.dp)
                )
                Column {
                    Text(
                        text = airQuality.title,
                        color = Color(0xFF234195),
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = airQuality.value,
                        color = Color(0xFF7685BF)
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AirQualityBox2() {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        AirQualityDataList(
            rainProb = 51.6,
            temp = 17.2,
            wind = 25.2,
            uvIndex = 9.0,
            humidity = 49.3,
            pressure = 1007.5
        ) { listOfAirQuality ->
            listOfAirQuality.forEach { airQuality ->
                Surface(
                    modifier = Modifier
                        .size(120.dp)
                        .customShadow(
                            color = Color(0xFF2B8DE5),
                            alpha = .8f,
                            borderRadius = 32.dp,
                            shadowRadius = 12.dp,
                            offsetY = 9.dp,
                            offsetX = 1.dp
                        ),
                    shape = RoundedCornerShape(30.dp),
                    color = ColorSurface,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(airQuality.icon),
                            contentDescription = airQuality.title,
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = airQuality.title,
                            color = Color(0xFF234195),
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                        Text(
                            text = airQuality.value,
                            color = Color(0xFF7685BF)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AirQualityBox3(modifier: Modifier= Modifier) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AirQualityDataList(
            rainProb = 51.6,
            temp = 17.2,
            wind = 25.2,
            uvIndex = 9.0,
            humidity = 49.3,
            pressure = 1007.5
        ) { listOfAirQuality ->
            listOfAirQuality.forEach { airQuality ->
                ConstraintLayout(
                    modifier = Modifier.size(90.dp)
                ) {
                    val (glassBackground, content) = createRefs()
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .constrainAs(glassBackground) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .fillMaxSize()
                            .customShadow(
                                color = Color(0xFF7598D2),
                                alpha = .3f,
                                borderRadius = 32.dp,
                                shadowRadius = 5.dp,
                                offsetY = 6.dp,
                                offsetX = 1.dp
                            )
                            .background(
                                brush=Brush.verticalGradient(
                                    listOf(
                                        Color.White.copy(alpha = 0.95f),
                                        Color.White.copy(alpha = 0.05f)
                                    )
                                ),
                                shape = RoundedCornerShape(32.dp)
                            )
                    )
                    Column(
                        modifier = Modifier
                            .constrainAs(content) {
                                top.linkTo(glassBackground.top)
                                bottom.linkTo(glassBackground.bottom)
                                start.linkTo(glassBackground.start)
                                end.linkTo(glassBackground.end)
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(airQuality.icon),
                            contentDescription = airQuality.title,
                            modifier = Modifier.size(30.dp)
                        )
                        Text(
                            text = airQuality.title,
                            color = Color(0xFF234195),
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(vertical = 5.dp),
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = airQuality.value,
                            color = Color(0xFF7685BF),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF5BBCFF)
@Composable
private fun AirQualityPreview() {
    AirQualityBox3()
}