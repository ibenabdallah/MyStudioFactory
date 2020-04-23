package com.smartdevservice.mystudiofactory.framework.network

import com.smartdevservice.mystudiofactory.domain.UserResponse
import retrofit2.Response
import retrofit2.http.GET

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

interface ApiService {

    @GET("trombi")
    suspend fun getAllUsers(): Response<UserResponse?>

}

const val BASE_API_URL = "https://dev.mystudiofactory.com/"