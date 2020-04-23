package com.smartdevservice.mystudiofactory.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/*
 * Created by ibenabdallah on 23/04/2020.
 * Copyright (c) 2020 ibenabdallah. All rights reserved.
 */

@Entity(indices = [Index(value = ["id"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "id")
    val id: String?,
    val name: String?,
    val description: String?,
    val picture: String?,
    val hired: String?)