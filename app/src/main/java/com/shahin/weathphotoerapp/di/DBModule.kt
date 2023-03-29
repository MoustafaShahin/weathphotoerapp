package com.shahin.weathphotoerapp.di

import android.app.Application
import androidx.room.Room
import com.shahin.weathphotoerapp.common.Constns.DB_NAME
import com.shahin.weathphotoerapp.data.local.AppDataBase
import com.shahin.weathphotoerapp.data.local.dao.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBModule {




    @Singleton
    @Provides
    fun providesDataBase(application: Application):AppDataBase{
        return Room.databaseBuilder(
            application,
            AppDataBase::class.java,
            DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideDAO(appDataBase: AppDataBase)
    :WeatherDao {
        return  appDataBase.weatherDao()
    }
}