package com.manage1_event.event.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(EventTable::class, BookingTable::class), version = 3, exportSchema = false)
abstract class EventDB : RoomDatabase(){

    abstract fun getDao() : EventDao

    abstract fun getBookingDao() : BookingDao

    companion object{
        private var instance : EventDB? = null

        @Synchronized
        fun getInstance(context: Context) : EventDB? {
            if(instance==null)
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventDB::class.java,
                    "event_db"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            return instance!!
        }


    }

}