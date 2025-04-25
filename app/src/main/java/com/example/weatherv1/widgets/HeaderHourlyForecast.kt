package com.example.weatherv1.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.utils.customShadow

@Composable
fun HeaderHourlyForecast(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White.copy(alpha = .2f), shape = RoundedCornerShape(10.dp))
            .customShadow(
                color = Color(0xFF2B8DE5),
                alpha = .2f,
                borderRadius = 10.dp,
                shadowRadius = 12.dp,
                offsetY = 7.dp
            )
            .padding(vertical = 5.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Today",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF234195)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                onClick()
            }
        ) {
            Text(
                text = "7-Day Forecasts", style = MaterialTheme.typography.labelMedium,
                color = Color(0xFF3156B2)
            )
            Icon(
                painter = painterResource( R.drawable.right_arrow),
                contentDescription = stringResource(R.string.arrow_right),
                tint = Color(0xFF3156B2),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}