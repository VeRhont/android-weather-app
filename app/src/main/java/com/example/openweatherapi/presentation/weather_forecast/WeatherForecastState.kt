package com.example.openweatherapi.presentation.weather_forecast

import com.example.openweatherapi.data.remote.dto.ListItem


data class WeatherForecastState(
    val isLoading: Boolean = false,
    val weatherForecast: List<List<ListItem>>? = null,
    val error: String = ""
)