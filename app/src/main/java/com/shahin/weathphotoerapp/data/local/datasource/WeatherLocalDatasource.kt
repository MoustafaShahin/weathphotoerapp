package com.shahin.weathphotoerapp.data.local.datasource

import com.shahin.weathphotoerapp.data.ICoroutineDispatchers
import com.shahin.weathphotoerapp.data.local.dao.WeatherDao
import com.shahin.weathphotoerapp.data.local.entities.WeatherEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherLocalDatasource
@Inject constructor(
    private val dao: WeatherDao,
    private val coroutineScopeDispatchers: ICoroutineDispatchers
) : IWeatherLocalDatasource {

    override suspend fun saveWeatherItem(item: WeatherEntity) {
        return withContext(coroutineScopeDispatchers.IO) {
            dao.saveWeatherItem(item)
        }
    }

    override suspend fun getWeatherItems(): Flow<List<WeatherEntity>?> {
        return withContext(coroutineScopeDispatchers.IO) {
            dao.getWeatherItems()
        }
    }

}