package com.example.openweatherapi.presentation.main_screen

import com.example.openweatherapi.domain.model.CurrentWeather


data class CurrentWeatherState(
    val isLoading: Boolean = false,
    val currentWeather: CurrentWeather? = null,
    val error: String = ""
)


