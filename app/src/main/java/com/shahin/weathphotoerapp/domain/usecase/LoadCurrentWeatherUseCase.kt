package com.shahin.weathphotoerapp.domain.usecase

import com.shahin.weathphotoerapp.data.repository.IWeatherRepository
import com.shahin.weathphotoerapp.domain.mapper.RemoteMapper
import com.shahin.weathphotoerapp.domain.model.WeatherItem
import javax.inject.Inject

class LoadCurrentWeatherUseCase @Inject constructor(private val repository: IWeatherRepository) {
    suspend operator fun invoke(latLng: String): WeatherItem? {
        return RemoteMapper.mapToDomain(repository.getCurrentWeatherData(latLng))
    }
}