package com.shahin.weathphotoerapp.ui.addItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahin.weathphotoerapp.domain.usecase.GetCurrentLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.distinctUntilChanged

import javax.inject.Inject


@HiltViewModel

class AddNewLogItemViewModel @Inject constructor(
    private  val getCurrentLocationUseCase: GetCurrentLocationUseCase
): ViewModel() {
init {
    getCurrentLocation()
}
    private fun getCurrentLocation(){
        viewModelScope.launch {
            getCurrentLocationUseCase().distinctUntilChanged().collect{location->
                location?.let {
                    it.latitude.toString()
                   /* _currentLocation.value=location
                    loadCurrentWeatherData("${location.latitude},${location.latitude}")*/
                }
            }
        }
    }
}