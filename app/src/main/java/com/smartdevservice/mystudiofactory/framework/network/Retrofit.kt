package com.smartdevservice.mystudiofactory.framework.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import timber.log.Timber

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */


inline fun <reified T> initRetrofitClient(okHttpClient: OkHttpClient, endpoint: String): T {
    return Retrofit.Builder()
        .baseUrl(endpoint)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create()
}

fun initOkHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Timber.tag("OkHttp").d(message)
        }
    })

    logging.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient().newBuilder()
        .addNetworkInterceptor(logging)
        .build()
}
