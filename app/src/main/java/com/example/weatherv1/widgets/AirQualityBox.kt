package com.example.weatherv1.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.weatherv1.model.AirQualityDataList
import com.example.weatherv1.utils.customShadow


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AirQualityBox(modifier: Modifier= Modifier) {
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
                    modifier = Modifier.size(87.dp)
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
                            fontWeight = FontWeight.W400,
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = airQuality.value,
                            color = Color(0xFF7685BF),
                            fontSize = 10.sp
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
    AirQualityBox()
}