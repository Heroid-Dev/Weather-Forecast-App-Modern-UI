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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
    val hideKeyboard= LocalSoftwareKeyboardController.current
    TextField(
        modifier = modifier.customShadow(
            color = Color.Black,
            alpha = .2f,
            borderRadius = 17.dp,
            shadowRadius = 5.dp,
            offsetY = 3.dp
        ),
        value = searchText,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(18.dp),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color(0xFFFFFFFF),
            focusedContainerColor = Color.White,
            unfocusedLabelColor = Color(0xFF3C6686),
            focusedLabelColor = Color(0xFF497CA4)
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
                hideKeyboard?.hide()
                onSearch()
            }
        )
    )
}