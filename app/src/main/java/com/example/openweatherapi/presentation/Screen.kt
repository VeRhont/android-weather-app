package com.example.openweatherapi.presentation

import kotlinx.serialization.Serializable

sealed class Screen(val route: String) {
    object CurrentWeatherScreen: Screen("current_weather_screen")
    object WeatherForecastScreen: Screen("weather_forecast_screen")
}


@Serializable
object ScreenA


@Serializable
data class ScreenB(
    val city: String
)
