package com.example.openweatherapi.presentation.weather_forecast.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.openweatherapi.data.remote.dto.ListItem
import java.util.Locale


@Composable
fun WeatherForDay(
    list: List<ListItem>,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Red),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = list[0].dtTxt.split(" ")[0]
        )

        LazyRow (
            modifier = modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(Green),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(list) { item ->
                WeatherCard(
                    temperature = item.main.temp,
                    weather = item.weather[0].main,
                    icon = item.weather[0].icon,
                    time = item.dtTxt.substring(11, 16)
                )
            }
        }
    }
}


@Composable
fun WeatherCard(
    temperature: Double,
    weather: String,
    icon: String,
    time: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(120.dp)
            .background(Cyan)
            .padding(8.dp)
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = time,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "${temperature}°C",
                style = MaterialTheme.typography.headlineSmall
            )
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/${icon}@2x.png"),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = weather.lowercase(Locale.ROOT),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}