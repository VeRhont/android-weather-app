package com.example.openweatherapi.presentation.current_weather

import com.example.openweatherapi.domain.model.CurrentWeather


data class CurrentWeatherState(
    val isLoading: Boolean = false,
    val currentWeather: CurrentWeather? = null,
    val error: String = ""
)


