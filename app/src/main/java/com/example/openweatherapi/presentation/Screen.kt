package com.example.openweatherapi.presentation

sealed class Screen(val route: String) {
    object CurrentWeatherScreen: Screen("current_weather_screen")
    object WeatherForecastScreen: Screen("weather_forecast_screen")
}