package com.example.openweatherapi.domain.repository

import com.example.openweatherapi.data.remote.dto.CurrentWeatherDto
import com.example.openweatherapi.data.remote.dto.WeatherForecastDto


interface WeatherRepository {

    suspend fun getCurrentWeather(city: String, token: String, units: String): CurrentWeatherDto

    suspend fun getWeatherForecast(city: String, token: String, units: String): WeatherForecastDto
}


