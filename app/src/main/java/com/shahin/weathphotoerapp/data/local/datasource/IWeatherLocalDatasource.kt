package com.shahin.weathphotoerapp.data.local.datasource

import com.shahin.weathphotoerapp.data.local.entities.WeatherEntity
import kotlinx.coroutines.flow.Flow


interface IWeatherLocalDatasource {
    suspend fun saveWeatherItem(item: WeatherEntity)

    suspend fun getWeatherItems(): Flow<List<WeatherEntity>?>
}