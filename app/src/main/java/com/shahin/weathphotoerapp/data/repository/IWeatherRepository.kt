package com.shahin.weathphotoerapp.data.repository

import com.shahin.weathphotoerapp.data.local.entities.WeatherEntity
import com.shahin.weathphotoerapp.data.remote.dto.CurrentWeatherDTO
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {

    suspend fun saveWeatherItem(item: WeatherEntity)
    suspend fun getWeatherItems(): Flow<List<WeatherEntity>?>
    suspend fun getCurrentWeatherData(latLng: String): CurrentWeatherDTO?

}