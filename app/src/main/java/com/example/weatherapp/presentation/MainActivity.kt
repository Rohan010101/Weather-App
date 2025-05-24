package com.example.weatherapp.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherapp.presentation.ui.DarkBlue
import com.example.weatherapp.presentation.ui.DeepBlue
import com.example.weatherapp.presentation.ui.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    // ARL: allows to register for activity results or permission requests
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(                 // rFAR: allows to request permissions & receive the result (whether user granted/denied the permissions) asynchronously
            ActivityResultContracts.RequestMultiplePermissions()        // a contract used to request multiple permissions at once. Defines the action of asking permission & returns the result as a map of permission names(Strings) to Boolean
        ) {
            viewModel.loadWeatherInfo()
        }

        // triggers the permission request that has been initialized with rFAR. Displays dialog asking for permissions
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))


        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(DarkBlue)
                        ) {
                            WeatherCard(
                                state = viewModel.state,
                                backgroundColor = DeepBlue
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            WeatherForecast(state = viewModel.state)
                        }

                        if (viewModel.state.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        viewModel.state.error?.let { error ->
                            Text(
                                text = error,
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}

