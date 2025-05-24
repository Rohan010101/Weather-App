package com.example.weatherapp.domian.weather

import java.time.LocalDateTime

// Actual WeatherData Format
data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)
