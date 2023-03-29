package com.shahin.weathphotoerapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shahin.weathphotoerapp.Constns.DB_VERSION
import com.shahin.weathphotoerapp.data.local.dao.WeatherDao
import com.shahin.weathphotoerapp.data.local.entities.WeatherEntity

@Database(entities = [WeatherEntity::class], version = DB_VERSION, exportSchema = false)
abstract class AppDataBase :RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}