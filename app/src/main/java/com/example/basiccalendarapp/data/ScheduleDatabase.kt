package com.example.basiccalendarapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ScheduleEntry::class], version = 1, exportSchema = false)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var Instance: ScheduleDatabase? = null
        fun getInstance(context: Context): ScheduleDatabase =
            Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ScheduleDatabase::class.java, "schedule_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }.also { Instance = it }
    }
}