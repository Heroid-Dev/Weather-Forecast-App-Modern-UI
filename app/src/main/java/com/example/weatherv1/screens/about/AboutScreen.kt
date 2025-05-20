package com.example.weatherv1.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.widgets.InfiniteColorBackground
import com.example.weatherv1.widgets.TopAppBarComponent
import android.provider.Settings
import android.content.Intent
import android.net.Uri

@Composable
fun AboutScreen(
    navigateToMainScreen: () -> Unit
) {
    val context= LocalContext.current
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

        ) {
            TopAppBarComponent(
                modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
                title = {
                    Text(text = "About",
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = Color(0xFF234195),
                        fontFamily = FontFamily.SansSerif)
                },
                navigationIcon = R.drawable.left_arrow,
                onNavigationClicked = navigateToMainScreen,
                actionIcon = R.drawable.info_icon,
                onActionClicked = {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", context.packageName, null)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(intent)
                },
                horizontalArrangement = Arrangement.SpaceBetween
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
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
    AboutScreen(navigateToMainScreen = {})
}