package com.example.openweatherapi.presentation.main_screen.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.openweatherapi.presentation.main_screen.CurrentWeatherViewModel
import com.example.openweatherapi.presentation.theme.OpenWeatherAPITheme
import java.util.Locale


@Composable
fun CurrentWeatherScreen(
//    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CurrentWeatherViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Yellow)
    ) {

        state.currentWeather?.let {

            val temperature = it.main.temp
            val feelsLike = it.main.feelsLike
            val weather = it.weather[0].main
            val wind = it.wind.speed
            val humidity = it.main.humidity
            val pressure = it.main.pressure

            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                item {
                    Text(
                        text = "Moscow",
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
                                    painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/${it.weather[0].icon}@4x.png"),
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
                        Button(onClick = {}) {
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