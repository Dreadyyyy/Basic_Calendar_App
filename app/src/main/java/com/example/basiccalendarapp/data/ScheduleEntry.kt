package com.example.basiccalendarapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class ScheduleEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timeInMinutes: Int,
    val date: String,
    val entryName: Int
)
