package com.shahin.weathphotoerapp.domain.usecase

import com.shahin.weathphotoerapp.data.repository.IWeatherRepository
import com.shahin.weathphotoerapp.domain.mapper.LocalMapper
import com.shahin.weathphotoerapp.domain.model.WeatherItem
import javax.inject.Inject

class AddNewItemToLogUseCase @Inject constructor(private val repository: IWeatherRepository) {
    suspend operator fun invoke(item: WeatherItem) {
        LocalMapper.mapFromDomain(item)?.let {
            repository.saveWeatherItem(it)
        }
    }
}