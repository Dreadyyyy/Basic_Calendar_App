package com.example.basiccalendarapp.data

import android.content.Context

interface AppContainer {
    val scheduleRepository: ScheduleRepository
    val alarmScheduler: AlarmScheduler
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val scheduleRepository: ScheduleRepository by lazy {
        OfflineScheduleRepository(ScheduleDatabase.getInstance(context).scheduleDao())
    }
    override val alarmScheduler: AlarmScheduler = CalendarNotificationScheduler(context)
}