package com.example.basiccalendarapp.data

import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getAllScheduleEntriesOfADateStream(date: String): Flow<List<ScheduleEntry>>
    fun getOneScheduleEntryStream(id: Int): Flow<ScheduleEntry?>
    suspend fun insertScheduleEntry(entry: ScheduleEntry)
    suspend fun deleteScheduleEntry(entry: ScheduleEntry)
    suspend fun updateScheduleEntry(entry: ScheduleEntry)
}