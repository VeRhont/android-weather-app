package com.example.openweatherapi.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy")
    return sdf.format(Date())
}