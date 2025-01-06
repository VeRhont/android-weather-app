package com.example.openweatherapi.domain.use_case.get_current_weather

import com.example.openweatherapi.common.Resource
import com.example.openweatherapi.data.remote.dto.toCurrentWeather
import com.example.openweatherapi.domain.model.CurrentWeather
import com.example.openweatherapi.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(city: String, token: String, units: String): Flow<Resource<CurrentWeather>> = flow {
        try {
            emit(Resource.Loading<CurrentWeather>())
            val currentWeather = repository.getCurrentWeather(city, token, units).toCurrentWeather()
            emit(Resource.Success<CurrentWeather>(currentWeather))
        } catch (e: HttpException) {
            emit(Resource.Error<CurrentWeather>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<CurrentWeather>("Couldn't reach server. Check your Internet connection"))
        }
    }
}
