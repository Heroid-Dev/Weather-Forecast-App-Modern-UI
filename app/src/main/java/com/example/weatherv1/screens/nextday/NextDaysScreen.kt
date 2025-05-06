package com.example.weatherv1.screens.nextday

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
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
fun NextDaysScreen(navigateToMainScreen: () -> Unit) {
    InfiniteColorBackground(
        color1 = Color(0xFF5BBCFF),
        color2 = Color(0xFF686CFF),
        color3 = Color(0xFF686CFF),
        color4 = Color(0xFF5BBCFF)
    ) { color1, color2 ->
        BelowBackground(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(color1, color2),
                        start = Offset(0f, 0f),
                        end = Offset.Infinite
                    )
                )
        )
        {
            TopBarNextDaysScreen(
                onBackClicked=navigateToMainScreen
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .fillMaxWidth()
        ) {
            WeatherCard(modifier = Modifier.fillMaxWidth().height(350.dp).padding(top = 100.dp))
            HeaderDaysOfTheWeek(text="EveryDays")
            RowsOfDaysOfTheWeek()
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun NextDaysScreenPreview() {
    NextDaysScreen() {}
}