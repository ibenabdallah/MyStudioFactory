package com.smartdevservice.mystudiofactory.data.repository

import com.smartdevservice.mystudiofactory.data.datasource.LocalDataSource
import com.smartdevservice.mystudiofactory.data.datasource.RemoteDataSource
import com.smartdevservice.mystudiofactory.domain.User
import timber.log.Timber

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

class UsersRepositoryImp (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : UsersRepository {

    override suspend fun getAllUsers(): List<User>? {
        if(localDataSource.getAllUsers().isNullOrEmpty()) {
            Timber.d("local data source is null")
            localDataSource.insertUsers(remoteDataSource.getAllUsers())
        }
        Timber.d("return local data source")
        return localDataSource.getAllUsers()?.sortedBy { it.name }
    }

    override suspend fun syncFullUser(): List<User>? {
        localDataSource.insertUsers(remoteDataSource.getAllUsers())
        return localDataSource.getAllUsers()?.sortedBy { it.name }
    }
}