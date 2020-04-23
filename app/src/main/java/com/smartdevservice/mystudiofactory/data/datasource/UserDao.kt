package com.smartdevservice.mystudiofactory.data.datasource

import androidx.room.*
import com.smartdevservice.mystudiofactory.domain.User

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers() : List<User>?

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserById(id: String) : User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>?)

}