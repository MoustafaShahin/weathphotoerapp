package com.shahin.weathphotoerapp.domain.mapper


import com.shahin.weathphotoerapp.domain.model.WeatherItem
import com.shahin.weathphotoerapp.data.local.entities.WeatherEntity
import com.shahin.weathphotoerapp.data.remote.dto.CurrentWeatherDTO
import java.security.SecureRandom


object RemoteMapper: IMapper<CurrentWeatherDTO, WeatherItem> {
    override fun mapToDomain(item: CurrentWeatherDTO?): WeatherItem? {
        return if (item==null) null else WeatherItem(
            id = SecureRandom().nextLong(),
            temp = item.current?.temp_c,
            description = item.current?.condition?.text,
            iconUrl = item.current?.condition?.icon,
            cityName = item.location?.name,
            photoPath = null
        )
    }

    override fun mapFromDomain(item: WeatherItem?): CurrentWeatherDTO? {
        //No thing for now
        return null
    }

}

object LocalMapper: IMapper<WeatherEntity, WeatherItem> {
    override fun mapToDomain(item: WeatherEntity?): WeatherItem? {
        return if (item==null) null else WeatherItem(
            id = item.id,
            temp = item.temp,
            description = item.description,
            iconUrl = item.icon,
            cityName = item.cityName,
            photoPath = item.photoPath
        )
    }

    override fun mapFromDomain(item: WeatherItem?): WeatherEntity? {
        return if (item==null) null else WeatherEntity(
            id = item.id,
            temp = item.temp,
            description = item.description,
            icon = item.iconUrl,
            cityName = item.cityName,
            photoPath = item.photoPath
        )
    }

}