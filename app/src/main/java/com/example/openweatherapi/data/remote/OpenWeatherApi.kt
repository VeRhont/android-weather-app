package com.example.openweatherapi.data.remote

import com.example.openweatherapi.data.remote.dto.CurrentWeatherDto
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenWeatherApi {

    @GET("/weather?q={city}&appid={}&units=metric&lang={lang}")
    suspend fun getCurrentWeatherInCity(
        @Path("city") city: String,
        @Path("token") token: String,
        @Path("lang") lang: String,
    ): CurrentWeatherDto

}