package com.smartdevservice.mystudiofactory.framework.network

import com.smartdevservice.mystudiofactory.data.datasource.RemoteDataSource
import com.smartdevservice.mystudiofactory.domain.User
import timber.log.Timber

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

class RemoteDataSourceImpl (private val apiService: ApiService) : RemoteDataSource {

    override suspend fun getAllUsers(): List<User>? {
        val response = apiService.getAllUsers()
        return when {
            response.isSuccessful -> {
                Timber.d("response is successful")
                when (response.body() != null) {
                    true -> {
                        Timber.d("response.body() is not null")
                        response.body()?.list
                    }
                    else -> {
                        Timber.d("response.body() is null")
                        null
                    }
                }
            }
            else -> {
                Timber.d("response is not successful")
                null
            }
        }
    }
}