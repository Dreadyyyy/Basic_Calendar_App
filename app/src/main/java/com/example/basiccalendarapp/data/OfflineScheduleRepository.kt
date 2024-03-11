package com.example.basiccalendarapp.data

import kotlinx.coroutines.flow.Flow

class OfflineScheduleRepository(
    private val scheduleDao: ScheduleDao
) : ScheduleRepository {
    override fun getAllScheduleEntriesOfADateStream(date: String): Flow<List<ScheduleEntry>> =
        scheduleDao.getAllEntriesOfADate(date)

    override fun getOneScheduleEntryStream(id: Int): Flow<ScheduleEntry?> =
        scheduleDao.getOneEntry(id)

    override suspend fun insertScheduleEntry(entry: ScheduleEntry) =
        scheduleDao.insertNewEntry(entry)

    override suspend fun deleteScheduleEntry(entry: ScheduleEntry) =
        scheduleDao.deleteEntry(entry)

    override suspend fun updateScheduleEntry(entry: ScheduleEntry) =
        scheduleDao.updateEntry(entry)

}