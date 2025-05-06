package com.example.weatherv1.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.widgets.InfiniteColorBackground

@Composable
fun AboutScreen() {
    InfiniteColorBackground(
        color1 = Color(0xFFEDFAFF),
        color2 = Color(0xFF9FD6FF),
        color3 = Color(0xFF5BBCFF),
        color4 = Color(0xFFEFF9FF)
    ) { color1, color2 ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(color1, color2),
                        start = Offset(0f, 0f),
                        end = Offset.Infinite
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(30.dp)),
                    painter = painterResource(R.drawable.weathericon),
                    contentDescription = "weatherIcon"
                )
                Text(
                    text = "Weather App", fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Text(
                    text = "Version 1.0", fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Gray
                )
            }

        }
    }
}

@Preview
@Composable
private fun AboutScreenPreview() {
    AboutScreen()
}