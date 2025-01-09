package com.example.openweatherapi.presentation.weather_forecast

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweatherapi.BuildConfig
import com.example.openweatherapi.common.Resource
import com.example.openweatherapi.data.remote.dto.ListItem
import com.example.openweatherapi.domain.use_case.get_weather_forecast.GetWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(WeatherForecastState())
    val state: State<WeatherForecastState> = _state


    init {
//        Log.d("CITY", savedStateHandle.get<String>("city") ?: "NULL")
//        val city = "Moscow"
//        getWeatherForecast(city)

        savedStateHandle.get<String>("city")?.let { city ->
            getWeatherForecast(city)
        }
    }

    private fun getWeatherForecast(
        city: String,
        token: String = BuildConfig.API_KEY,
        units: String = "metric",
    ) {
        getWeatherForecastUseCase(city, token, units).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = WeatherForecastState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = WeatherForecastState(isLoading = true)
                }

                is Resource.Success -> {

                    val result1 = result.data?.list
                    var i = 0

                    result1?.let {

                        val today = result1[0].dtTxt.split(" ")[0]

                        while (result1[i].dtTxt.split(" ")[0] == today) {
                            i++
                        }

                        val a = (i..< result1.count()-8+i step 9).map { j ->
                            result1.subList(j, j + 9)
                        }.toList()

                        val resultF = listOf(result1.subList(0, i)) + a

                        _state.value = WeatherForecastState(weatherForecast = resultF)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}