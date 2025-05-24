package com.example.weatherapp.domian.weather

// WeatherInfo extends WeatherData (uses WeatherData format for weatherDataPerDay & currentWeatherData)
data class WeatherInfo(
    val weatherDataPerDay: Map<Int,List<WeatherData>>,      // on day1, the value of weatherInfo val are ___, on day2, ......
    val currentWeatherData: WeatherData?
)
