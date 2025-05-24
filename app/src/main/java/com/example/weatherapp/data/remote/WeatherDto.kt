package com.example.weatherapp.data.remote

import com.squareup.moshi.Json

// WeatherDto extends WeatherDataDto (in a particular hour, apply WeatherDataDto)
data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)
