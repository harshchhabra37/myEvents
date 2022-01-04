package com.manage1_event.event.database

import androidx.room.*

@Dao
interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(booking : BookingTable)

    @Delete
    suspend fun delete(booking : BookingTable)

    @Query("Select email from BookingTable where id=:id")
    fun getEmail(id : Int) : String
}