package com.example.basiccalendarapp

import android.app.Application
import androidx.compose.ui.text.intl.Locale
import com.example.basiccalendarapp.data.AppContainer
import com.example.basiccalendarapp.data.AppDataContainer

class BasicCalendarApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}