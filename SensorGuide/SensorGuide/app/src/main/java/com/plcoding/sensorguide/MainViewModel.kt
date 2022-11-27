package com.plcoding.sensorguide

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sensorsCollection: ISensorCollection
) : ViewModel() {
    private val lightSensor = sensorsCollection.sensors[0]
    private val gyroscopeSensor = sensorsCollection.sensors[1]
    private val proximitySensor = sensorsCollection.sensors[2]
    private val accelerometerSensor = sensorsCollection.sensors[3]
    private val gpsSensor = sensorsCollection.sensors[4]

    var isDark by mutableStateOf(false)
    var isVeryDark by mutableStateOf(false)
    var lux by mutableStateOf(0f)
    var x by mutableStateOf(0f)
    var y by mutableStateOf(0f)
    var z by mutableStateOf(0f)
    var proximity by mutableStateOf(0f)
    var acceleration by mutableStateOf(0f)
    var lat by mutableStateOf(0f)
    var lon by mutableStateOf(0f)

    init{
        lightSensor.startListening()
        gyroscopeSensor.startListening()

        proximitySensor.startListening()
        accelerometerSensor.startListening()
        gpsSensor.startListening()

        lightSensor.setOnSensorValuesChangedListener {
            values ->
            val luxValue = values[0]
            isDark = luxValue < 60f
            isVeryDark = luxValue < 30f
            lux=luxValue

        }

        gyroscopeSensor.setOnSensorValuesChangedListener {
                values ->
            x = values[0]
            y = values[1]
            z = values[2]

        }

        gpsSensor.setOnSensorValuesChangedListener {
                values ->
            lat = values[0]
            lon = values[1]

        }

        proximitySensor.setOnSensorValuesChangedListener {
                values ->
            proximity = values[0]

        }


        accelerometerSensor.setOnSensorValuesChangedListener {
                values ->
            acceleration = values[0]

        }
    }

}