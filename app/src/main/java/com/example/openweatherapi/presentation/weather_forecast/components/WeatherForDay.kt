package com.example.openweatherapi.presentation.weather_forecast.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openweatherapi.common.round
import com.example.openweatherapi.data.remote.dto.ListItem
import com.example.openweatherapi.common.getDate
import com.example.openweatherapi.common.getTime
import com.example.openweatherapi.presentation.theme.FontColorDark


@Composable
fun WeatherForDay(
    list: List<ListItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = list[0].getDate(),
            fontSize = 20.sp,
            color = FontColorDark
        )

        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .height(130.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(list) { item ->
                WeatherCard(
                    temperature = item.main.temp.round(1),
                    weather = item.weather[0].main,
                    icon = item.weather[0].icon,
                    time = item.getTime()
                )
            }
        }
    }
}


