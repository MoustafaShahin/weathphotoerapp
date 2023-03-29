package com.shahin.weathphotoerapp.domain.usecase

import com.shahin.weathphotoerapp.data.repository.IWeatherRepository
import com.shahin.weathphotoerapp.domain.mapper.LocalMapper
import com.shahin.weathphotoerapp.domain.model.WeatherItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RetreiveWeatherLogUseCase @Inject constructor(private val repository: IWeatherRepository) {
    suspend operator fun invoke(): Flow<List<WeatherItem?>?> {
        return repository.getWeatherItems().map { listItem ->
            listItem?.map { LocalMapper.mapToDomain(it) }
        }
    }
}