package com.shahin.weathphotoerapp.data.remote.dto

data class CurrentWeatherDTO(
    val current: Current? = null,
    val location: Location? = null
)