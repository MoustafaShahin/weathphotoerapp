package com.shahin.weathphotoerapp.data.remote.datasource

import com.shahin.weathphotoerapp.data.ICoroutineDispatchers
import com.shahin.weathphotoerapp.data.remote.NetworkUtil
import com.shahin.weathphotoerapp.data.remote.dto.CurrentWeatherDTO
import com.shahin.weathphotoerapp.data.remote.service.WeatherServicesApi
import com.shahin.weathphotoerapp.data.remote.datasource.IWeatherRemoteDatasource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRemoteDatasource @Inject constructor (
    private val api: WeatherServicesApi,
    private val coroutineScopeDispatchers: ICoroutineDispatchers
    ): IWeatherRemoteDatasource {
    override suspend fun currentWeather(latLng: String): CurrentWeatherDTO? {
        return withContext(coroutineScopeDispatchers.IO){
            NetworkUtil.processAPICall { api.currentWeather(latLng) }
        }
    }
}