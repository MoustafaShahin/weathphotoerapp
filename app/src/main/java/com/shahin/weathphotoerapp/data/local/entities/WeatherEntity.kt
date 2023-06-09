package com.shahin.weathphotoerapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WeatherItems")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val temp: Double? = null,
    val description: String? = null,
    val icon: String? = null,
    val cityName: String? = null,
    val photoPath: String? = null
)