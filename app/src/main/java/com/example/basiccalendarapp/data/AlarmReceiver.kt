package com.example.basiccalendarapp.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.basiccalendarapp.data.utils.EXTRA_MESSAGE

private const val NOTIFICATION: String = "NOTIFICATION"
class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra(EXTRA_MESSAGE) ?: return
        Log.d(NOTIFICATION, message)
    }

}