package com.example.weatherapp.domian.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}