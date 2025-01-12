package com.example.openweatherapi.presentation.current_weather.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.openweatherapi.common.round
import com.example.openweatherapi.presentation.ScreenWeatherForecast
import com.example.openweatherapi.presentation.current_weather.CurrentWeatherViewModel
import com.example.openweatherapi.presentation.theme.BackgroundColor
import com.example.openweatherapi.presentation.theme.BackgroundColorDark
import com.example.openweatherapi.presentation.theme.ButtonColor
import com.example.openweatherapi.presentation.theme.FontColor
import com.example.openweatherapi.presentation.theme.FontColorDark
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
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BackgroundColorDark,
                        BackgroundColor
                    )
                )
            )
    ) {

        state.currentWeather?.let { state ->

            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                item {

                    Text(
                        text = "Today, ${viewModel.currentDate.value}",
                        fontSize = 18.sp,
                        color = FontColorDark
                    )

                    SearchField(viewModel)

                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "${state.main.temp.round(1)}°C",
                                fontSize = 60.sp,
                                fontWeight = FontWeight.Bold,
                                color = FontColor
                            )
                            Text(
                                text = "Feels like ${state.main.feelsLike.round(1)}°C",
                                fontSize = 20.sp,
                                color = FontColorDark
                            )
                            Spacer(modifier = Modifier.height(15.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/${state.weather[0].icon}@4x.png"),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(120.dp)
                                )
                                Text(
                                    text = state.weather[0].main.lowercase(Locale.ROOT),
                                    style = MaterialTheme.typography.headlineLarge,
                                    color = FontColor
                                )
                            }

                            HorizontalDivider(color = FontColorDark)

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Wind",
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = FontColorDark
                                    )

                                    Text(
                                        text = "${state.wind.speed} m/s",
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = FontColor
                                    )
                                }

                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Humidity",
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = FontColorDark
                                    )

                                    Text(
                                        text = "${state.main.humidity}%",
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = FontColor
                                    )
                                }

                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Pressure",
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = FontColorDark
                                    )

                                    Text(
                                        text = "${state.main.pressure} hPa",
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = FontColor
                                    )
                                }
                            }
                        }
                    }
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth().height(100.dp),
                        contentAlignment = Center
                    ) {
                        Button(
                            modifier = Modifier,
                            onClick = {
                                navController.navigate(
                                    ScreenWeatherForecast(city = viewModel.cityState.value)
                                )
                            },
                            shape = RoundedCornerShape(20),
                            colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
                        ) {
                            Text(
                                text = "Forecast for 5 days",
                                fontSize = 22.sp,
                                color = FontColor
                            )
                        }
                    }
                }
            }
        }


        if (state.error.isNotBlank()) {
            SearchField(viewModel)
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                fontSize = 24.sp,
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