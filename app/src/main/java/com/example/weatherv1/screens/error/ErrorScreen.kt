package com.example.weatherv1.screens.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weatherv1.R
import retrofit2.HttpException

@Composable
fun ErrorScreen(error: Exception) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("connectionlost.json"))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    val errorCode = if (error is HttpException) {
        error.code().toString()
    } else {
        "Unknown Error"
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.error_background),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier.size(250.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
                    Text(
                        text = "$errorCode:",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00409C)
                    )
                    Text(
                        text = "${error.message}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.W300,
                        color = Color(0xFF00409C)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen(error = Exception())
}