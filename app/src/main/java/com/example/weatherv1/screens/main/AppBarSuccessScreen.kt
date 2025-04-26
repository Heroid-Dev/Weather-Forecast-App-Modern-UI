package com.example.weatherv1.screens.main

import android.icu.text.DateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.widgets.TopAppBarButton
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarSuccessScreen(
    //weatherInfo: Weather?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TopAppBarButton(
            icon = R.drawable.menuicon,
            modifier = Modifier.size(70.dp),
            iconSize = 30.dp,
            iconDescription = "menu icon",
            onClickButton = {}
        )
        LocationInfo(city = "Rome", localTime = 1744041917)
        TopAppBarButton(
            icon = R.drawable.searchicon,
            modifier = Modifier.size(70.dp),
            iconSize = 30.dp,
            iconDescription = "search icon",
            onClickButton = {}
        )
    }
}


@Composable
fun LocationInfo(city: String,
                 localTime: Long) {
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


@Preview(showBackground = true)
@Composable
private fun AppBarSuccessPreview() {
    AppBarSuccessScreen()
}