package com.example.weatherv1.screens.search

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.weatherv1.utils.customShadow

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    searchText: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    TextField(
        modifier = modifier.customShadow(
            color = Color.Black,
            alpha = .2f,
            borderRadius = 35.dp,
            shadowRadius = 10.dp,
            offsetY = 2.dp
        ),
        value = searchText,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(25.dp),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        label = {
            Text(text = "search city")
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
            }
        )
    )
}