package com.example.openweatherapi.domain.model

import com.example.example.Clouds
import com.example.example.Main
import com.example.example.Weather
import com.example.example.Wind


data class CurrentWeather(
    val weather: ArrayList<Weather> = arrayListOf(),
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val name: String,
)