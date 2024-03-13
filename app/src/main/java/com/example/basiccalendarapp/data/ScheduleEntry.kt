package com.example.basiccalendarapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class ScheduleEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timeInMinutes: Int,
    val date: String,
    val entryName: String
)

fun ScheduleEntry.getShownTime(): String {
    var hours: String = "${timeInMinutes / 60}"
    if (hours.length == 1) hours = "0$hours"
    var minutes: String = "${timeInMinutes % 60}"
    if (minutes.length == 1) minutes = "0$minutes"
    return "$hours:$minutes"
}