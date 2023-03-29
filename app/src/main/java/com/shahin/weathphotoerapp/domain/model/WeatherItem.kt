package com.shahin.weathphotoerapp.domain.model

import android.os.Parcel
import android.os.Parcelable


data class WeatherItem(
    val id: Long? = null,
    val temp: Double? = null,
    val description: String? = null,
    val iconUrl: String? = null,
    val cityName: String? = null,
    val photoPath: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(temp)
        parcel.writeString(description)
        parcel.writeString(iconUrl)
        parcel.writeString(cityName)
        parcel.writeString(photoPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherItem> {
        override fun createFromParcel(parcel: Parcel): WeatherItem {
            return WeatherItem(parcel)
        }

        override fun newArray(size: Int): Array<WeatherItem?> {
            return arrayOfNulls(size)
        }
    }
}