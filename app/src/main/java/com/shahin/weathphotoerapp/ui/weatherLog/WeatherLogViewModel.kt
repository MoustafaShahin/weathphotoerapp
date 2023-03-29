package com.shahin.weathphotoerapp.ui.weatherLog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahin.weathphotoerapp.domain.model.WeatherItem
import com.shahin.weathphotoerapp.domain.usecase.RetreiveWeatherLogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherLogViewModel @Inject constructor(private val retreiveWeatherLogUseCase: RetreiveWeatherLogUseCase) :
    ViewModel() {
    private val _weatherItemsStateFlow = MutableStateFlow<List<WeatherItem?>?>(emptyList())
    val weatherItemsStateFlow: StateFlow<List<WeatherItem?>?> = _weatherItemsStateFlow

    init {
        getWeatherLog()
    }

    fun getWeatherLog() {
        viewModelScope.launch {
            retreiveWeatherLogUseCase().collect {
                _weatherItemsStateFlow.value = it
            }
        }
    }
}