package com.example.weatherv1.screens.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.widgets.TopAppBarButton

@Composable
fun TopBarSearchScreen() {
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
            iconDescription = stringResource(R.string.arrow_back),
            onClickButton = {}
        )
        TextTopBarSearchScreen()
    }
}