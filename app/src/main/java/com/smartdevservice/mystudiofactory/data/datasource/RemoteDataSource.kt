package com.smartdevservice.mystudiofactory.data.datasource

import com.smartdevservice.mystudiofactory.domain.User

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

interface RemoteDataSource {
    suspend fun getAllUsers() : List<User>?
}