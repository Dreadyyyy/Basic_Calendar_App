package com.example.basiccalendarapp.data

import android.content.Context

interface AppContainer {
    val scheduleRepository: ScheduleRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val scheduleRepository: ScheduleRepository by lazy {
        OfflineScheduleRepository(ScheduleDatabase.getInstance(context).scheduleDao())
    }
}