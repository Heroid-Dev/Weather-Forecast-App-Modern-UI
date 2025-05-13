package com.example.weatherv1.screens.setting

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherv1.R
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.utils.customShadow
import com.example.weatherv1.widgets.InfiniteColorBackground
import com.example.weatherv1.widgets.TopAppBarComponent

@Composable
fun SettingScreen(
    navigateToMainScreen: () -> Unit,
    mainViewModel: MainViewModel,
) {
    val unitPref = mainViewModel.unitPrefs.collectAsState().value

    var toggleState1 = remember { mutableStateOf(unitPref.isFahrenheit) }
    var toggleState2 = remember { mutableStateOf(unitPref.isMph) }
    var toggleState3 = remember { mutableStateOf(unitPref.inKPa) }
    var toggleState4 = remember { mutableStateOf(unitPref.inRain_mm) }

    var notificationsEnabled = mainViewModel.notificationEnabled.collectAsState().value

    val context= LocalContext.current
    val permissionLauncher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {isGranted->
            if (!isGranted){
                mainViewModel.updateNotificationEnabled(false)
            }else{
                mainViewModel.updateNotificationEnabled(true)
            }
        }
    )
    InfiniteColorBackground(
        color1 = Color(0xFFEDFAFF),
        color2 = Color(0xFF9FD6FF),
        color3 = Color(0xFF5BBCFF),
        color4 = Color(0xFFEFF9FF)
    ) { color1, color2 ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        listOf(color1, color2),
                        start = Offset(0f, 0f),
                        end = Offset.Infinite
                    )
                )
        ) {
            TopAppBarComponent(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp),
                title = {
                    Text(
                        modifier = Modifier.padding(start = 80.dp),
                        text = "Setting",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00409C)
                    )
                },
                navigationIcon = R.drawable.left_arrow,
                onNavigationClicked = navigateToMainScreen
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                SettingsCard(title = "Units") {
                    ToggleRow("Temperature", toggleState1.value, "Fahrenheit") {
                        toggleState1.value = it
                        mainViewModel.updateUnits(
                            unitPref.copy(isFahrenheit = it)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    ToggleRow("Wind Speed", toggleState2.value, "Miles per hour") {
                        toggleState2.value = it
                        mainViewModel.updateUnits(
                            unitPref.copy(isMph = it)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    ToggleRow("Air Pressure", toggleState3.value, "Kilopascal") {
                        toggleState3.value = it
                        mainViewModel.updateUnits(
                            unitPref.copy(inKPa = it)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    ToggleRow("Precipitation", toggleState4.value, "Millimeters") {
                        toggleState4.value = it
                        mainViewModel.updateUnits(
                            unitPref.copy(inRain_mm = it)
                        )
                    }
                }

                SettingsCard(title = "Notifications") {
                    ToggleRow("Notification", notificationsEnabled) {isChecked->
                        if (isChecked) {
                            val hasPermission =
                                ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.POST_NOTIFICATIONS
                                ) == PackageManager.PERMISSION_GRANTED

                            if (!hasPermission) {
                                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                            } else {
                                mainViewModel.updateNotificationEnabled(true)
                            }
                        } else {
                            mainViewModel.updateNotificationEnabled(false)
                        }
                    }
                }

                SettingsCard(title = "Forecast") {
                    ClickableRow("Days to Forecast")
                }

                SettingsCard(title = "More") {
                    ClickableRow("About")
                }
            }
        }
    }
}

@Composable
fun SettingsCard(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .customShadow(
                color = Color(0xFF4BAAEC),
                alpha = .6f,
                shadowRadius = 12.dp,
                borderRadius = 24.dp,
                offsetY = 5.dp
            )
            .background(Color(0x9ADEF2FF), shape = RoundedCornerShape(30.dp))
            .padding(vertical = 20.dp, horizontal = 16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00409C),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        content()
    }
}

@Composable
fun ToggleRow(
    label: String,
    isChecked: Boolean,
    subLabel: String? = null,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            if (label.isNotEmpty()) {
                Text(text = label, fontSize = 16.sp, color = Color(0xFF003366))
            }
            if (!subLabel.isNullOrEmpty()) {
                Text(text = subLabel, fontSize = 12.sp, color = Color.Gray)
            }
        }
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                uncheckedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF90CAF9),
                uncheckedTrackColor = Color(0xFFC9E3FF),
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}

@Composable
fun ClickableRow(label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* handle click */ },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 16.sp, color = Color(0xFF003366))
        Icon(painterResource(R.drawable.right_arrow), contentDescription = null, tint = Color.Gray)
    }
}


@Preview(showBackground = true)
@Composable
private fun SettingScreenPreview() {
    SettingScreen(mainViewModel = hiltViewModel(), navigateToMainScreen = {})
}
