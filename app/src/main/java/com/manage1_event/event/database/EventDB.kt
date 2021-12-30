package com.manage1_event.event.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EventTable::class], version = 2, exportSchema = false)
abstract class EventDB : RoomDatabase(){

    abstract fun getDao() : EventDao

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