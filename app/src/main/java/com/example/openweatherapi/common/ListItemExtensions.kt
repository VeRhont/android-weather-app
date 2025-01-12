package com.example.openweatherapi.common

import com.example.openweatherapi.data.remote.dto.ListItem


fun ListItem.getDate(): String {
    return this.dtTxt.substring(0, 10)
}


fun ListItem.getTime(): String {
    return this.dtTxt.substring(11, 16)
}