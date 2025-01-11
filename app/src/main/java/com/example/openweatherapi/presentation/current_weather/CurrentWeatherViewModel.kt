package com.example.openweatherapi.presentation.current_weather

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweatherapi.BuildConfig
import com.example.openweatherapi.common.Resource
import com.example.openweatherapi.domain.use_case.get_current_weather.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject



@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy")
    return sdf.format(Date())
}


@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : ViewModel() {

    private var _state = mutableStateOf(CurrentWeatherState())
    var state: State<CurrentWeatherState> = _state

    var cityState = mutableStateOf("Moscow")
        private set

    var currentDate = mutableStateOf(getCurrentDate())
        private set

    init {
        getCurrentWeather()
    }

    fun getCurrentWeather(
        city: String = cityState.value,
        token: String = BuildConfig.API_KEY,
        units: String = "metric",
    ) {
        getCurrentWeatherUseCase(city, token, units).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = CurrentWeatherState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CurrentWeatherState(isLoading = true)
                }

                is Resource.Success -> {
                    updateDate()
                    _state.value = CurrentWeatherState(currentWeather = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateCity(newCity: String) {
        cityState.value = newCity
    }

    private fun updateDate() {
        currentDate.value = getCurrentDate()
    }

}