package com.example.weatherapp.domian.repository

import com.example.weatherapp.domian.util.Resource
import com.example.weatherapp.domian.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}