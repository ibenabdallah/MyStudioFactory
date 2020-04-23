package com.smartdevservice.mystudiofactory.data.repository

import com.smartdevservice.mystudiofactory.data.datasource.RemoteDataSource
import com.smartdevservice.mystudiofactory.domain.User

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

class UsersRepositoryImp (
    private val remoteDataSource: RemoteDataSource
) : UsersRepository {

    override suspend fun getAllUsers(): List<User>? {
        return remoteDataSource.getAllUsers()?.sortedBy { it.name }
    }

    override suspend fun syncFullUser(): List<User>? {
        return remoteDataSource.getAllUsers()?.sortedBy { it.name }
    }
}