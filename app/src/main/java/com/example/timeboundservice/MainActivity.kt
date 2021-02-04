package com.example.timeboundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.timeboundservice.MyService.MyLocalBinder

class MainActivity : AppCompatActivity() {
    var myService: MyService? = null
    var isBound : Boolean= false

    fun showTime(view: View?) {
    val currentTime : String = myService?.getCurrentTime().toString()
       val txtTime : TextView = findViewById(R.id.time)
       txtTime.text = currentTime
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val i = Intent(this, MyService::class.java)
        bindService(i, connection, Context.BIND_AUTO_CREATE)

    }
    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {

            val binder:MyLocalBinder = service as MyLocalBinder
            myService = binder.service
            isBound = true
            Log.d("state","Binded")
        }
        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            Log.d("state","UnBound")
        }
    }
}