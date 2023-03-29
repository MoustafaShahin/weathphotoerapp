package com.shahin.weathphotoerapp.data.local.dao

import androidx.room.*
import com.shahin.weathphotoerapp.data.local.entities.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeatherItem(item: WeatherEntity)

    @Query("Select * From WeatherItems Order BY id Desc")
    fun getWeatherItems(): Flow<List<WeatherEntity>?>

}