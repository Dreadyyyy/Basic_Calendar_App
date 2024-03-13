package com.example.basiccalendarapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewEntry(scheduleEntry: ScheduleEntry)

    @Update
    suspend fun updateEntry(scheduleEntry: ScheduleEntry)

    @Delete
    suspend fun deleteEntry(scheduleEntry: ScheduleEntry)

    @Query("SELECT * FROM schedule WHERE id = :id")
    fun getOneEntry(id: Int): Flow<ScheduleEntry>

    @Query("SELECT * FROM schedule WHERE date = :date ORDER BY timeInMinutes ASC")
    fun getAllEntriesOfADate(date: String): Flow<List<ScheduleEntry>>
}