package com.manage1_event.event.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BookingTable", primaryKeys = ["id", "email"])
data class BookingTable (

    @ColumnInfo
    val id : Int,

    @ColumnInfo
    val email : String
    )