package com.example.openweatherapi.presentation.weather_forecast.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.openweatherapi.presentation.theme.BackgroundColor
import com.example.openweatherapi.presentation.theme.BackgroundColorDark
import com.example.openweatherapi.presentation.theme.FontColorDark
import com.example.openweatherapi.presentation.weather_forecast.WeatherForecastViewModel


@Composable
fun WeatherForecastScreen(
    city: String,
    modifier: Modifier = Modifier,
    viewModel: WeatherForecastViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val weatherForecastList = state.weatherForecast

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BackgroundColorDark,
                        BackgroundColor
                    )
                )
            )
            .padding(24.dp)

    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {

            item {
                weatherForecastList?.forEachIndexed { index, item ->
                    WeatherForDay(item)
                    if (index < weatherForecastList.size - 1) {
                        HorizontalDivider(
                            color = FontColorDark
                        )
                    }
                }
            }
        }
    }
}