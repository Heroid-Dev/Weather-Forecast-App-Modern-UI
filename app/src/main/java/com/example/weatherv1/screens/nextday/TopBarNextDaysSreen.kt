package com.example.weatherv1.screens.nextday

import android.icu.text.DateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherv1.R
import com.example.weatherv1.widgets.TopAppBarButton
import java.time.Instant
import java.util.Date

@Composable
fun TopBarNextDaysScreen() {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TopAppBarButton(
            modifier = Modifier.size(70.dp),
            icon = R.drawable.left_arrow,
            iconSize = 30.dp,
            iconDescription = "arrow_back",
            onClickButton = {}
        )
        Column(
            modifier = Modifier.padding(start = 35.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(

                text = "Next 7 Days Forecast",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Today: ${
                    DateFormat.getDateInstance(DateFormat.MONTH_FIELD).format(
                        Date.from(Instant.now())
                    )
                }",
                color = Color.Black.copy(alpha = .5f)
            )
        }
    }
}
