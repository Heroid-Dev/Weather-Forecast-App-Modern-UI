package com.example.weatherv1.screens.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherv1.widgets.BelowBackground
import com.example.weatherv1.widgets.InfiniteColorBackground
import com.example.weatherv1.widgets.WeatherCard

@Composable
fun SearchScreen(
    navigateToMainScreen: () -> Unit = {}
) {
    var cityFoundedState by remember { mutableStateOf(false) }
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
    InfiniteColorBackground(
        color1 = Color(0xFF5BBCFF),
        color2 = Color(0xFF686CFF),
        color3 = Color(0xFF686CFF),
        color4 = Color(0xFF5BBCFF),
    ) { color1, color2 ->


        BelowBackground(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .clip(RoundedCornerShape(bottomStart = 35.dp, bottomEnd = 35.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(color1, color2),
                        start = Offset(0f, 0f),
                        end = Offset.Infinite
                    )
                )
        ) {
            TopBarSearchScreen()
        }


        Box(
            modifier = Modifier.fillMaxSize(),
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
                    searchText = searchText,
                    onValueChange = {
                        searchText = it
                        if (it.isEmpty()) {
                            searchTriggered.value = false
                        }
                    },
                    onSearch = {
                        searchTriggered.value = true
                        cityFoundedState = true
                    },
                    modifier = Modifier.offset(y = textFieldOffsetY)
                )


                AnimatedVisibility(
                    visible = searchTriggered.value
                ) {
                    WeatherCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                            .animateEnterExit(
                                enter = slideInVertically(animationSpec = tween(1000),initialOffsetY = { it }) + fadeIn(),
                                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
                            )
                    )
                }


            }
        }
    }
}






@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreen()
}