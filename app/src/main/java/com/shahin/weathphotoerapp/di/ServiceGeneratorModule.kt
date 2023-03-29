package com.shahin.weathphotoerapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shahin.weathphotoerapp.Constns.NETWORK_CONNECTION_TIME_OUT
import com.shahin.weathphotoerapp.Constns.NETWORK_READ_TIME_OUT
import com.shahin.weathphotoerapp.data.remote.RetrofitServiceGenerator
import com.shahin.weathphotoerapp.data.remote.interceptor.AppInterceptor
import com.shahin.weathphotoerapp.data.remote.service.WeatherServicesApi
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

object ServiceGeneratorModule {
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideHttpOkClient(interceptor: AppInterceptor, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(NETWORK_READ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(NETWORK_CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
    @Singleton
    @Provides
    fun provideHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(
        gson: Gson,
        httpClient: OkHttpClient
    ):Retrofit {
        return RetrofitServiceGenerator(gson, httpClient).returnRetrofitInstance()
    }

    @Singleton
    @Provides
    fun provideUserDataApis(retrofit: Retrofit): WeatherServicesApi {
        return retrofit.create(WeatherServicesApi::class.java)
    }
}