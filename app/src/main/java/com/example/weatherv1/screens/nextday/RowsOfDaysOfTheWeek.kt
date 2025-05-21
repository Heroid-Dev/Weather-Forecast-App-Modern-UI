package com.example.weatherv1.screens.nextday

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherv1.R
import com.example.weatherv1.data.datastore.NextDaysState
import com.example.weatherv1.model.Day
import com.example.weatherv1.repositorys.MainViewModel
import com.example.weatherv1.utils.celsiusToFahrenheit
import com.example.weatherv1.utils.getDayOfWeekFromEpoch
import com.example.weatherv1.utils.getWeatherIconFromCondition


@Composable
fun RowsOfDaysOfTheWeek(days: List<Day>, mainViewModel: MainViewModel) {
    val unitPref = mainViewModel.unitPrefs.collectAsState().value
    val nextDaysState=mainViewModel.nextDaysState.collectAsState().value
    val trimmedDays=days.filterNotNull().dropLast(1)
    val visibleDays= when(nextDaysState){
        NextDaysState.next7days -> trimmedDays.take(7)
        NextDaysState.next15days -> trimmedDays
    }
    LazyColumn {
        items(
            items = visibleDays,
            key = {
                it.datetime
            }
        ) { infoDay ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(2f),
                    text = getDayOfWeekFromEpoch(infoDay.datetimeEpoch.toLong()),
                    fontWeight = FontWeight.W500,
                    color = Color(0xFF204B6E),
                    fontSize = MaterialTheme.typography.labelLarge.fontSize
                )
                Row(modifier = Modifier.weight(1.2f)) {
                    Image(
                        painter = painterResource(R.drawable.precip),
                        contentDescription = "Rain icon",
                        modifier = Modifier.size(20.dp),
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = if (unitPref.inRain_mm) "${infoDay.precip.toInt()}mm" else "${infoDay.precipprob.toInt()}%",
                        color = Color(0xFF679EE6),
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize
                    )
                }

                Row(
                    modifier = Modifier.weight(3f),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                        Image(
                            painter = painterResource(getWeatherIconFromCondition(infoDay.hours[12].icon)),
                            contentDescription = infoDay.conditions,
                            modifier = Modifier.height(17.dp),
                            contentScale = ContentScale.FillHeight
                        )
                        Image(
                            painter = painterResource(getWeatherIconFromCondition(infoDay.hours[20].icon)),
                            contentDescription = infoDay.conditions,
                            modifier = Modifier.height(17.dp),
                            contentScale = ContentScale.FillHeight
                        )

                    Text(
                        text = infoDay.conditions,
                        fontWeight = FontWeight.W400,
                        color = Color(0xFF204B6E),
                        fontSize = MaterialTheme.typography.labelSmall.fontSize
                    )
                }

                Text(
                    modifier = Modifier.weight(1.2f),
                    text = "${
                        if (unitPref.isFahrenheit)
                            celsiusToFahrenheit(infoDay.tempmax).toInt()
                                .toString() + "F" else "${infoDay.tempmax.toInt()}°"
                    }/${
                        if (unitPref.isFahrenheit) celsiusToFahrenheit(infoDay.tempmin).toInt()
                            .toString() + "F" else "${infoDay.tempmin.toInt()}°"
                    }",
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    color = Color(0xFF204B6E),
                    fontWeight = FontWeight.W600
                )
            }
        }
    }
}

