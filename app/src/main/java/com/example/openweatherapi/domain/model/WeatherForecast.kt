package com.example.openweatherapi.domain.model

import com.example.openweatherapi.data.remote.dto.City
import com.example.openweatherapi.data.remote.dto.ListItem


data class WeatherForecast(
    val list: ArrayList<ListItem> = arrayListOf(),
    val city: City
)