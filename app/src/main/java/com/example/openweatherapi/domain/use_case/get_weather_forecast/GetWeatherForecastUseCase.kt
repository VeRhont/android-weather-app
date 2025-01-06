package com.example.openweatherapi.domain.use_case.get_weather_forecast

import com.example.openweatherapi.common.Resource
import com.example.openweatherapi.data.remote.dto.toWeatherForecast
import com.example.openweatherapi.domain.model.WeatherForecast
import com.example.openweatherapi.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetWeatherForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(city: String, token: String, units: String): Flow<Resource<WeatherForecast>> = flow {
        try {
            emit(Resource.Loading<WeatherForecast>())
            val currentWeather = repository.getWeatherForecast(city, token, units).toWeatherForecast()
            emit(Resource.Success<WeatherForecast>(currentWeather))
        } catch (e: HttpException) {
            emit(Resource.Error<WeatherForecast>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<WeatherForecast>("Couldn't reach server. Check your Internet connection"))
        }
    }
}
