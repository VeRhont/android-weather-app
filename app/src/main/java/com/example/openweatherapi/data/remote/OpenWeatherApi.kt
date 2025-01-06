package com.example.openweatherapi.data.remote

import com.example.openweatherapi.data.remote.dto.CurrentWeatherDto
import com.example.openweatherapi.data.remote.dto.WeatherForecastDto
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherApi {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") q: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): CurrentWeatherDto

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("q") q: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): WeatherForecastDto
}
