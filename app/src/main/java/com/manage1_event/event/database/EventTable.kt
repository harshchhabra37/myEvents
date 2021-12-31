package com.manage1_event.event.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EventTable")
data class EventTable(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    @ColumnInfo
    val name : String,

    @ColumnInfo
    val date : String,

    @ColumnInfo
    val seatsLeft : Int,

    @ColumnInfo
    val desc : String,

    @ColumnInfo
    val place : String,

    @ColumnInfo
    val cost : Int
)