package com.example.weatherv1.widgets

import android.content.Intent
import android.icu.text.DateFormat
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.ui.theme.ColorSurface
import com.example.weatherv1.utils.customShadow
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

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
        MenuButton()
        LocationInfo(city = "Rome", localTime = 1744041917)
        ActionButton()
    }
}

@Composable
fun ActionButton() {
    Surface(
        modifier = Modifier
            .size(70.dp)
            .customShadow(
                color = Color(0xFF008FEF),
                alpha = .12f,
                borderRadius = 15.dp,
                shadowRadius =25.dp,
                offsetY = 1.dp
            )
            .padding(10.dp),
        color = ColorSurface.copy(alpha = .3f),
        shape = RoundedCornerShape(15.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painterResource(R.drawable.searchicon),
                contentDescription = stringResource(R.string.search_icon),
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun LocationInfo(city: String, localTime: Long) {
    val dateFormat= DateFormat.getDateInstance(DateFormat.ERA_FIELD)
    val formatted=dateFormat.format(Date(localTime * 1000))

    Column(
        modifier = Modifier.padding(top=12.dp),
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
        Text(text = formatted,
            fontSize = MaterialTheme.typography.labelMedium.fontSize,
            color = Color(0xFF234195).copy(alpha = .7f)
            )
    }
}

@Composable
fun MenuButton() {
    Surface(
        modifier = Modifier
            .size(70.dp)
            .customShadow(
                color = Color(0xFF008FEF),
                alpha = .12f,
                borderRadius = 15.dp,
                shadowRadius =25.dp,
                offsetY = 1.dp
            )
            .padding(10.dp),
        color = ColorSurface.copy(alpha = .3f),
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painterResource(R.drawable.menuicon),
                contentDescription = stringResource(R.string.menu_icon),
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AppBarSuccessPreview() {
    AppBarSuccessScreen()
}