package com.example.openweatherapi.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.openweatherapi.presentation.current_weather.components.CurrentWeatherScreen
import com.example.openweatherapi.presentation.theme.OpenWeatherAPITheme
import com.example.openweatherapi.presentation.weather_forecast.components.WeatherForecastScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OpenWeatherAPITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
//                        startDestination = Screen.CurrentWeatherScreen.route
                        startDestination = ScreenA
                    ) {

                        composable<ScreenA> {
                            CurrentWeatherScreen(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        composable<ScreenB> {
                            val city = it.toRoute<ScreenB>().city
                            WeatherForecastScreen(
                                city = city,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

//                        composable (
//                            route = Screen.CurrentWeatherScreen.route
//                        ) {
//                            CurrentWeatherScreen(
//                                navController = navController,
//                                modifier = Modifier.padding(innerPadding)
//                            )
//                        }

//                        composable (
//                            route = Screen.WeatherForecastScreen.route
//                        ) {
//                            WeatherForecastScreen(
//                                modifier = Modifier.padding(innerPadding)
//                            )
//                        }