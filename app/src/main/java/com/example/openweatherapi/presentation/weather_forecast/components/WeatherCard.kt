package com.example.openweatherapi.presentation.weather_forecast.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.openweatherapi.presentation.theme.CardColor
import com.example.openweatherapi.presentation.theme.FontColor
import java.util.Locale


@Composable
fun WeatherCard(
    temperature: Double,
    weather: String,
    icon: String,
    time: String,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = modifier
            .fillMaxHeight()
            .width(130.dp)
            .background(Color.Transparent)
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardColor,
        ),
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = time,
                fontSize = 12.sp,
                color = FontColor
            )
            Text(
                text = "${temperature}°C",
                fontSize = 22.sp,
                color = FontColor
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/${icon}@2x.png"),
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
                Text(
                    text = weather.lowercase(Locale.ROOT),
                    fontSize = 16.sp,
                    color = FontColor
                )
            }
        }
    }
}