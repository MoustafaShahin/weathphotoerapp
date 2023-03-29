package com.shahin.weathphotoerapp.data.remote.service

import com.shahin.weathphotoerapp.data.remote.dto.CurrentWeatherDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServicesApi {

    @GET("current.json")
    suspend fun currentWeather(
        @Query("q") latLng:String
    ):Response<CurrentWeatherDTO>
}