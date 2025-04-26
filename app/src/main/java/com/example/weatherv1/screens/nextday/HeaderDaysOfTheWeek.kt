package com.example.weatherv1.screens.nextday

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HeaderDaysOfTheWeek(text: String) {
    Text(
        modifier = Modifier.padding(start = 8.dp, top = 50.dp, bottom = 5.dp),
        text = text,
        fontSize = MaterialTheme.typography.titleMedium.fontSize,
        fontWeight = FontWeight.Bold
    )
}