package com.example.openweatherapi.data.repository

import com.example.openweatherapi.data.remote.OpenWeatherApi
import com.example.openweatherapi.data.remote.dto.CurrentWeatherDto
import com.example.openweatherapi.data.remote.dto.WeatherForecastDto
import com.example.openweatherapi.domain.repository.WeatherRepository
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi,
) : WeatherRepository {

    override suspend fun getCurrentWeather(
        city: String,
        token: String,
        units: String
    ): CurrentWeatherDto {
        return api.getCurrentWeather(city, token, units)
    }

    override suspend fun getWeatherForecast(
        city: String,
        token: String,
        units: String,
    ): WeatherForecastDto {
        return api.getWeatherForecast(city, token, units)
    }
}