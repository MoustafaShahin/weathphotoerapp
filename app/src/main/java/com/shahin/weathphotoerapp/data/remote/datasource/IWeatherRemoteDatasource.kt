package com.shahin.weathphotoerapp.data.remote.datasource

import com.shahin.weathphotoerapp.data.remote.dto.CurrentWeatherDTO

interface IWeatherRemoteDatasource {
    suspend fun currentWeather(latLng:String): CurrentWeatherDTO?
}