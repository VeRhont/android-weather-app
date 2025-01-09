package com.example.openweatherapi.presentation.current_weather.components

import android.util.Log
import android.view.KeyEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.openweatherapi.common.round
import com.example.openweatherapi.presentation.Screen
import com.example.openweatherapi.presentation.ScreenB
import com.example.openweatherapi.presentation.current_weather.CurrentWeatherViewModel
import java.util.Locale


@Composable
fun CurrentWeatherScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CurrentWeatherViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Yellow)
    ) {

        state.currentWeather?.let { state ->

            val temperature = state.main.temp.round(1)
            val feelsLike = state.main.feelsLike.round(1)
            val weather = state.weather[0].main
            val wind = state.wind.speed
            val humidity = state.main.humidity
            val pressure = state.main.pressure

            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                item {
                    TextField(
                        value = viewModel.cityState.value,
                        onValueChange = {
                            viewModel.updateCity(it)
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(),
                        modifier = Modifier.onKeyEvent {
                            if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                                viewModel.getCurrentWeather()
                                true
                            }
                            false
                        }
                    )
                    Text(
                        text = viewModel.cityState.value,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    HorizontalDivider()
                    Text(
                        text = "Today 06.01.2025",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Box(
                        modifier = modifier
                            .fillMaxWidth()
//                            .height(350.dp)
                            .background(Green)
                            .padding(24.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "${temperature}°C",
                                fontSize = 60.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Feels like ${feelsLike}°C",
                                fontSize = 20.sp,
                            )
                            Spacer(modifier = Modifier.height(15.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
//                                    .background(Red)
                                    .height(100.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/${state.weather[0].icon}@4x.png"),
                                    contentDescription = null,
                                    modifier = Modifier.size(128.dp)
                                )
                                Text(
                                    text = weather.lowercase(Locale.ROOT),
                                    style = MaterialTheme.typography.headlineLarge
                                )
                            }

                            HorizontalDivider()

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 10.dp)
//                                    .height(50.dp)
                                    .background(Red),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Wind",
                                        style = MaterialTheme.typography.headlineSmall
                                    )

                                    Text(
                                        text = "$wind m/s",
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                }

                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Humidity",
                                        style = MaterialTheme.typography.headlineSmall
                                    )

                                    Text(
                                        text = "$humidity%",
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                }

                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Pressure",
                                        style = MaterialTheme.typography.headlineSmall
                                    )

                                    Text(
                                        text = "$pressure hPa",
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                }



                            }

                        }

                    }

                    Box(
                        modifier = Modifier.fillMaxWidth().height(100.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize().background(Cyan)
                        ) {

                        }
                    }


                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Blue),
                        contentAlignment = Center
                    ) {
                        Button(onClick = {
//                            navController.navigate(Screen.WeatherForecastScreen.route)
                            navController.navigate(
                                ScreenB(city = viewModel.cityState.value)
                            )
                        }) {
                            Text(
                                text = "Forecast for 5 days",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                }
            }

        }


        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}