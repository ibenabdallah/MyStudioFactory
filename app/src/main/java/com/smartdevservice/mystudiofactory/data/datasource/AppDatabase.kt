package com.smartdevservice.mystudiofactory.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smartdevservice.mystudiofactory.domain.User

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

const val DATABASE_NAME = "my_db_msf"

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}