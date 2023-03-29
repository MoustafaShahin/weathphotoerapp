package com.shahin.weathphotoerapp.data.repository

import com.shahin.weathphotoerapp.data.remote.datasource.IWeatherRemoteDatasource
import com.shahin.weathphotoerapp.data.local.datasource.IWeatherLocalDatasource
import com.shahin.weathphotoerapp.data.local.entities.WeatherEntity
import com.shahin.weathphotoerapp.data.remote.dto.CurrentWeatherDTO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val remoteDatasource: IWeatherRemoteDatasource,
    private val localDatasource: IWeatherLocalDatasource
)
    :IWeatherRepository {
    override suspend fun saveWeatherItem(item: WeatherEntity) {
        localDatasource.addWeatherItem(item)
    }

    override suspend fun getWeatherItems(): Flow<List<WeatherEntity>?> {
        return localDatasource.getWeatherItems()
    }

    override suspend fun getCurrentWeatherData(latLng: String): CurrentWeatherDTO? {
        return remoteDatasource.currentWeather(latLng)

    }

}