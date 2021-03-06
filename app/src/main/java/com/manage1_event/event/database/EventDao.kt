package com.manage1_event.event.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event : EventTable)

    @Delete
    suspend fun delete(event : EventTable)

    @Update
    fun update(event : EventTable)

    @Query("Select * From EventTable")
    fun getAllEvents() : List<EventTable>

    @Query("Select * from EventTable")
    fun read() : LiveData<List<EventTable>>

    @Query("Select  * from EventTable Where id = :id")
    fun getEvent(id : Int) : EventTable

    @Query("Update EventTable Set seatsLeft = seatsLeft-1 Where id = :id")
    fun bookEvent(id : Int)

}