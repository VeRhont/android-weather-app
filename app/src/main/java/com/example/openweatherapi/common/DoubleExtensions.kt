package com.example.openweatherapi.common

import kotlin.math.pow


fun Double.round(decimalPlaces: Int): Double {
    val n = 10.0.pow(decimalPlaces.toDouble())
    return Math.round(this * n) / n
}