package com.shahin.weathphotoerapp.data.remote.interceptor


import com.shahin.weathphotoerapp.Constns.AUTHORIZATION_HEADER
import com.shahin.weathphotoerapp.Constns.LANG_HEADER
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject

class AppInterceptor @Inject constructor() :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request().url.newBuilder()
            .addQueryParameter(LANG_HEADER, getDefaultLanguage())
            .addQueryParameter(AUTHORIZATION_HEADER, "04678963fb944b32a21225126232703")
            .build()

        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }

    private fun getDefaultLanguage():String{
        return Locale.getDefault().language
    }
}
