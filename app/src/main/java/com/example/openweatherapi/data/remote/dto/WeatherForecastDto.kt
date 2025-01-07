package com.example.openweatherapi.data.remote.dto

import com.example.openweatherapi.domain.model.WeatherForecast


data class WeatherForecastDto(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: ArrayList<ListItem> = arrayListOf(),
    val city: City
)


fun WeatherForecastDto.toWeatherForecast(): WeatherForecast {
    return WeatherForecast(
        list = list,
    )
}
