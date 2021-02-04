package com.example.timeboundservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.*

class MyService : Service() {

    private val binder: IBinder = MyLocalBinder()
    //this will bind client to server or user to service.

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    fun getCurrentTime(): String? {
        val dateFormat = SimpleDateFormat("HH:mm:ss")
        dateFormat.timeZone = TimeZone.getTimeZone("Asia/Kolkata")
        return dateFormat.format(Date())
    }

    class MyLocalBinder : Binder() {
        val service: MyService
            get() {
                return MyService()
            }
    }
}