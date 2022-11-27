package com.plcoding.sensorguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.sensorguide.ui.theme.SensorGuideTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensorGuideTheme {
                var viewModel = viewModel<MainViewModel>()
                val isDark = viewModel.isDark
                val isVeryDark = viewModel.isVeryDark
                val lux = viewModel.lux
                val x = viewModel.x
                val y = viewModel.y
                val z = viewModel.z
                val lat = viewModel.lat
                val lon = viewModel.lon
                val proximity = viewModel.proximity
                val acceleration = viewModel.acceleration
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            if(isVeryDark) Color.Black else if(isDark) Color.DarkGray else Color.White
                        ),
                contentAlignment  = Alignment.Center

                ){
                    Text(
                        text = (if(isVeryDark) {"It's very dark outside. Lux: "+lux.toString()} else if(isDark) {"It's dark outside . Lux: "+lux.toString()} else {"It's bright outside. Lux: "+lux.toString()})+
                                "\n Coordinates: \nX:"+x+
                                "\n Y:"+y+
                                " \nZ:"+z+
                                " \nAcceleration:"+acceleration+
                                " \nProximity:"+proximity+
                                "\n Lat:"+lat+
                                " \nLon:"+lon
                        ,color = if(isDark) Color.White else Color.DarkGray
                    )
                }
            }
        }
    }
}