package com.shahin.weathphotoerapp.ui.addItem

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahin.weathphotoerapp.common.resource.Resource
import com.shahin.weathphotoerapp.domain.model.WeatherItem
import com.shahin.weathphotoerapp.domain.usecase.AddNewItemToLogUseCase
import com.shahin.weathphotoerapp.domain.usecase.GetCurrentLocationUseCase
import com.shahin.weathphotoerapp.domain.usecase.LoadCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class AddNewLogItemViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val loadCurrentWeatherUseCase: LoadCurrentWeatherUseCase,
    private val addNewLogItemUseCase: AddNewItemToLogUseCase
) : ViewModel() {
    private val _currentLocation = MutableStateFlow<Location?>(null)
    val currentLocation: StateFlow<Location?> = _currentLocation

    private val _currentWeatherDataStateFlow =
        MutableStateFlow<Resource<WeatherItem?>>(Resource.Initial())
    val currentWeatherDataStateFlow: StateFlow<Resource<WeatherItem?>> =
        _currentWeatherDataStateFlow

    private val _save = MutableStateFlow<Boolean>(false)
    val saveState: StateFlow<Boolean> = _save
    fun getCurrentLocation() {
        viewModelScope.launch {
            getCurrentLocationUseCase().distinctUntilChanged().collect { location ->
                location?.let {
                    _currentLocation.value = location
                    getCurrentWeatherData("${location.latitude},${location.latitude}")
                }
            }
        }
    }

    private fun getCurrentWeatherData(latLag: String) {
        viewModelScope.launch {
            _currentWeatherDataStateFlow.value = Resource.Loading()
            try {
                val weatherData = loadCurrentWeatherUseCase(latLag)
                _currentWeatherDataStateFlow.value = Resource.SUCCESS(weatherData)
            } catch (t: Throwable) {
                _currentWeatherDataStateFlow.value = Resource.ERROR(t)
            }

        }
    }

    fun saveItemToLog(item: WeatherItem) {
        viewModelScope.launch {
            addNewLogItemUseCase(item)
            _save.value = true
        }
    }
}