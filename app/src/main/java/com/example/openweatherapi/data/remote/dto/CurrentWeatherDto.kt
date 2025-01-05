package com.example.openweatherapi.data.remote.dto

import com.example.example.Clouds
import com.example.example.Coord
import com.example.example.Main
import com.example.example.Sys
import com.example.example.Weather
import com.example.example.Wind
import com.example.openweatherapi.domain.model.CurrentWeather


data class CurrentWeatherDto(
    val coord: Coord,
    val weather: ArrayList<Weather> = arrayListOf(),
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int,
)


fun CurrentWeatherDto.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        weather = weather,
        main = main,
        visibility = visibility,
        wind = wind,
        clouds = clouds,
        name = name
    )
}