package com.example.openweatherapi.presentation

import kotlinx.serialization.Serializable


@Serializable
object ScreenCurrentWeather


@Serializable
data class ScreenWeatherForecast(
    val city: String
)
