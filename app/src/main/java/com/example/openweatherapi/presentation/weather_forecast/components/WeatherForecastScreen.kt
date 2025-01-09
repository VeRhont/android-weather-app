package com.example.openweatherapi.presentation.weather_forecast.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.openweatherapi.domain.model.WeatherForecast
import com.example.openweatherapi.presentation.weather_forecast.WeatherForecastViewModel


@Composable
fun WeatherForecastScreen(
    city: String,
    modifier: Modifier = Modifier,
    viewModel: WeatherForecastViewModel = hiltViewModel(),
) {
    Log.d("Here", "WeatherScreen")
    val state = viewModel.state.value
    val weatherForecastList = state.weatherForecast

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
            .background(Yellow)
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {

            item {
                weatherForecastList?.forEach { item ->
                    WeatherForDay(item)
                }
            }

        }
    }
}