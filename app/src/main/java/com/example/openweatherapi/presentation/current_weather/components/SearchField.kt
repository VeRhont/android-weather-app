package com.example.openweatherapi.presentation.current_weather.components

import android.view.KeyEvent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openweatherapi.presentation.current_weather.CurrentWeatherViewModel
import com.example.openweatherapi.presentation.theme.ButtonColor
import com.example.openweatherapi.presentation.theme.FontColorDark


@Composable
fun SearchField(viewModel: CurrentWeatherViewModel) {
    TextField(
        value = viewModel.cityState.value,
        textStyle = TextStyle.Default.copy(
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        ),
        onValueChange = {
            viewModel.updateCity(it)
        },
        trailingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Enter city")
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .onKeyEvent {
                if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                    viewModel.getCurrentWeather()
                    true
                }
                false
            },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            unfocusedIndicatorColor = FontColorDark,
            focusedIndicatorColor = FontColorDark,

        )
    )
}