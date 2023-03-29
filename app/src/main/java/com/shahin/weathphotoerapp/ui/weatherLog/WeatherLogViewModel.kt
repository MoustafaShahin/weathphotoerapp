package com.shahin.weathphotoerapp.ui.weatherLog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahin.weathphotoerapp.domain.model.WeatherItem
import com.shahin.weathphotoerapp.domain.usecase.RetreiveWeatherLogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherLogViewModel  @Inject constructor( private val retreiveWeatherLogUseCase: RetreiveWeatherLogUseCase) :ViewModel() {
     var weatherLogLiveData = MutableLiveData<List<WeatherItem?>>()

    init {
        getWeatherLog()
    }

    fun getWeatherLog() {
        viewModelScope.launch {
            retreiveWeatherLogUseCase().collect{
                weatherLogLiveData.postValue( it)
            }
        }
    }
}