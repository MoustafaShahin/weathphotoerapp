package com.shahin.weathphotoerapp.data.remote

import com.google.gson.Gson
import com.shahin.weathphotoerapp.Constns.NETWORK_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitServiceGenerator @Inject constructor(
    private val gson: Gson,
    private val httpClient: OkHttpClient) {





    fun returnRetrofitInstance()  :Retrofit{
      return  Retrofit.Builder()
            .baseUrl(NETWORK_BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}
