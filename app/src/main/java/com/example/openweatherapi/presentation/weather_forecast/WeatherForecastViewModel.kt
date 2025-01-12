package com.example.openweatherapi.presentation.weather_forecast

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweatherapi.BuildConfig
import com.example.openweatherapi.common.Resource
import com.example.openweatherapi.common.getDate
import com.example.openweatherapi.data.remote.dto.ListItem
import com.example.openweatherapi.domain.use_case.get_weather_forecast.GetWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(WeatherForecastState())
    val state: State<WeatherForecastState> = _state

    init {
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
                    result.data?.list?.let {
                        _state.value = WeatherForecastState(
                            weatherForecast = getWeatherForecast(it)
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getWeatherForecast(resultList: ArrayList<ListItem>): List<List<ListItem>> {
        val currentDate = resultList[0].getDate()

        // Index of the last entry of the current date
        var lastEntryIndex = 0
        while (resultList[lastEntryIndex].getDate() == currentDate) {
            lastEntryIndex++
        }

        val currentDateForecast = listOf(resultList.subList(0, lastEntryIndex))

        val otherDatesForecast =
            (lastEntryIndex..<resultList.count() - 8 + lastEntryIndex step 8).map { j ->
                resultList.subList(j, j + 8)
            }.toList()

        return currentDateForecast + otherDatesForecast
    }
}