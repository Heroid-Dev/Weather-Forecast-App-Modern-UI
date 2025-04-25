package com.example.weatherv1.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherv1.ui.theme.ColorSurface
import com.example.weatherv1.utils.customShadow

@Composable
fun TopAppBarButton(
    @DrawableRes icon: Int,
    modifier: Modifier= Modifier,
    iconSize: Dp,
    iconDescription: String,
    onClickButton:()-> Unit
) {
    Surface(
        modifier = modifier
            .customShadow(
                color = Color(0xFF008FEF),
                alpha = .12f,
                borderRadius = 15.dp,
                shadowRadius = 25.dp,
                offsetY = 1.dp
            )
            .padding(10.dp),
        color = ColorSurface.copy(alpha = .3f),
        shape = RoundedCornerShape(15.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .clickable{
                    onClickButton()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painterResource(icon),
                contentDescription = iconDescription,
                tint = Color.Black,
                modifier = Modifier.size(iconSize)
            )
        }
    }
}