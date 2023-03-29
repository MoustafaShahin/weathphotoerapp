package com.shahin.weathphotoerapp.di

import com.shahin.weathphotoerapp.data.remote.datasource.WeatherRemoteDatasource
import com.shahin.weathphotoerapp.data.remote.service.WeatherServicesApi
import com.shahin.weathphotoerapp.data.remote.datasource.IWeatherRemoteDatasource
import com.shahin.weathphotoerapp.data.AppCoroutineDispatchers
import com.shahin.weathphotoerapp.data.ICoroutineDispatchers
import com.shahin.weathphotoerapp.data.local.dao.WeatherDao
import com.shahin.weathphotoerapp.data.local.datasource.IWeatherLocalDatasource
import com.shahin.weathphotoerapp.data.local.datasource.WeatherLocalDatasource
import com.shahin.weathphotoerapp.data.repository.IWeatherRepository
import com.shahin.weathphotoerapp.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun providesCoroutineDispatcher():ICoroutineDispatchers{
        return  AppCoroutineDispatchers()
    }
    @Singleton
    @Provides
    fun provideWeatherLocalDataSource(weatherDao: WeatherDao, coroutineDispatchers: ICoroutineDispatchers):IWeatherLocalDatasource{
        return WeatherLocalDatasource(weatherDao,coroutineDispatchers)
    }
    @Singleton
    @Provides
    fun provideWeatherRemoteDataSource(servicesApi: WeatherServicesApi, coroutineDispatchers: ICoroutineDispatchers):IWeatherRemoteDatasource{
        return WeatherRemoteDatasource(servicesApi,coroutineDispatchers)
    }

    @Singleton
    @Provides
    fun providesWeatherRepository(remoteDatasource: IWeatherRemoteDatasource,
                                  localWeatherLocalDatasource: IWeatherLocalDatasource
    )
    : IWeatherRepository {
        return WeatherRepository(remoteDatasource,localWeatherLocalDatasource)

    }




}