package com.shahin.weathphotoerapp

import android.location.Location
import com.shahin.weathphotoerapp.common.resource.Resource
import com.shahin.weathphotoerapp.domain.model.WeatherItem
import com.shahin.weathphotoerapp.domain.usecase.AddNewItemToLogUseCase
import com.shahin.weathphotoerapp.domain.usecase.GetCurrentLocationUseCase
import com.shahin.weathphotoerapp.domain.usecase.LoadCurrentWeatherUseCase
import com.shahin.weathphotoerapp.ui.addItem.AddNewLogItemViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class AddNewLogItemViewModelTest {
    private lateinit var addNewLogItemViewModelTest: AddNewLogItemViewModel
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase = mockk()
    private val loadCurrentWeatherUseCase: LoadCurrentWeatherUseCase = mockk()
    private val addNewLogItemUseCase: AddNewItemToLogUseCase = mockk()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        addNewLogItemViewModelTest = AddNewLogItemViewModel(
            getCurrentLocationUseCase, loadCurrentWeatherUseCase, addNewLogItemUseCase
        )
        MockKAnnotations.init(this)
    }

    @Test
    fun `get weather data by location return successful WeatherItem`() = runTest {
        var loc = Location("55.3,44.3")
        loc.longitude = 0.0
        loc.latitude = 0.0

        coEvery { getCurrentLocationUseCase.invoke() } returns flow {

            emit(loc)
        }
        var expected = WeatherItem(
            description = "adad",
            temp = 56.2, photoPath = "kjalkfal", cityName = "City Name"
        )
        coEvery {
            loadCurrentWeatherUseCase.invoke(any())
        } returns expected

        addNewLogItemViewModelTest.currentWeatherDataStateFlow.test {
            addNewLogItemViewModelTest.getCurrentLocation()
            it.assertInitializedAndLoading()
            assertTrue(it[2] is Resource.SUCCESS)
            Assert.assertEquals(expected, it[2].data)
        }

    }
}