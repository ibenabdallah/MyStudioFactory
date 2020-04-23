package com.smartdevservice.mystudiofactory.data.repository

import com.smartdevservice.mystudiofactory.domain.User

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

interface UsersRepository {
    suspend fun getAllUsers() : List<User>?
    suspend fun syncFullUser() : List<User>?
}