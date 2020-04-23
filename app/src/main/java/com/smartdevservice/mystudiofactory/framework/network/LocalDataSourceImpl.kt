package com.smartdevservice.mystudiofactory.framework.network

import com.smartdevservice.mystudiofactory.data.datasource.LocalDataSource
import com.smartdevservice.mystudiofactory.data.datasource.UserDao
import com.smartdevservice.mystudiofactory.domain.User

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

class LocalDataSourceImpl (private val userDao: UserDao) : LocalDataSource {

    override suspend fun getAllUsers(): List<User>? {
        return userDao.getAllUsers()

    }

    override suspend fun insertUsers(users: List<User>?) {
        userDao.insertUsers(users)
    }
}