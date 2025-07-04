package com.example.weatherv1.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBarComponent(
    modifier: Modifier= Modifier,
    title: @Composable () -> Unit,
    @DrawableRes navigationIcon: Int? = null,
    onNavigationClicked: () -> Unit = {},
    @DrawableRes actionIcon: Int? = null,
    onActionClicked: () -> Unit = {},
    horizontalArrangement: Arrangement.Horizontal= Arrangement.Start,
    verticalAlignment: Alignment.Vertical= Alignment.CenterVertically,
    content:@Composable ()-> Unit={}
) {
    Column(modifier= Modifier.fillMaxWidth()) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment
        ) {
            navigationIcon?.let { navigation ->
                TopAppBarButton(
                    icon = navigation,
                    modifier = Modifier.size(70.dp),
                    iconSize = 30.dp,
                    iconDescription = "navigation icon",
                    onClickButton = onNavigationClicked
                )
            }
            title()
            actionIcon?.let { action ->
                TopAppBarButton(
                    icon = action,
                    modifier = Modifier.size(70.dp),
                    iconSize = 30.dp,
                    iconDescription = "search icon",
                    onClickButton = onActionClicked
                )
            }
        }
        content()
    }
}


//@Composable
//fun LocationInfo(
//    city: String,
//    localTime: Long,
//) {
//    val dateFormat = DateFormat.getDateInstance(DateFormat.ERA_FIELD)
//    val formatted = dateFormat.format(Date(localTime * 1000))
//
//    Column(
//        modifier = Modifier.padding(top = 12.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(5.dp)
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(5.dp)
//        ) {
//            Image(
//                painter = painterResource(R.drawable.location),
//                contentDescription = stringResource(R.string.location_icon),
//                modifier = Modifier.size(30.dp)
//            )
//            Text(
//                text = city,
//                fontWeight = FontWeight.Bold,
//                fontSize = MaterialTheme.typography.titleLarge.fontSize,
//                color = Color(0xFF234195),
//                fontFamily = FontFamily.SansSerif
//            )
//        }
//        Text(
//            text = formatted,
//            fontSize = MaterialTheme.typography.labelMedium.fontSize,
//            color = Color(0xFF234195).copy(alpha = .7f)
//        )
//    }
//}
