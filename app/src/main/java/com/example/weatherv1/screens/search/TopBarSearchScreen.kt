package com.example.weatherv1.screens.search

import android.icu.text.DateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherv1.R
import com.example.weatherv1.widgets.TopAppBarComponent
import java.time.Instant
import java.util.Date

@Composable
fun TopBarSearchScreen(
    onClickBackButton: () -> Unit,
) {
    TopAppBarComponent(
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
        title = {
            TextTopBarSearchScreen()
        },
        navigationIcon = R.drawable.left_arrow,
        onNavigationClicked = onClickBackButton
    )
}

@Composable
fun TextTopBarSearchScreen() {
    Column(
        Modifier.padding(start = 50.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Search City",
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