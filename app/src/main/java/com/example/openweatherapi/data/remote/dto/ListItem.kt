package com.example.openweatherapi.data.remote.dto

import com.example.example.Clouds
import com.example.example.Weather
import com.example.example.Wind
import com.google.gson.annotations.SerializedName


data class ListItem(
    val dt: Long,
    val main: Main2,
    val weather: ArrayList<Weather> = arrayListOf(),
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Int,
    val sys: Sys2,
    @SerializedName("dt_txt")
    val dtTxt: String
)
