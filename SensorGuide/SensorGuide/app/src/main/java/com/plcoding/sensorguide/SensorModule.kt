package com.plcoding.sensorguide

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule{

    @Provides
    @Singleton
    fun provideSensorCollection(app: Application): ISensorCollection{
        return SensorCollection(
            mutableListOf(
                LightSensor(app.applicationContext),
                GyroscopeSensor(app.applicationContext),
                ProximitySensor(app.applicationContext),
                AccelerometerSensor(app.applicationContext),
                GPSSensor(app.applicationContext)
            ) )
    }
}