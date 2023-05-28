package com.example.notificationdemo.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.notificationdemo.cronometro

class MyReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action)
        {
            "Pause" -> cronometro.pausarCronometro()
        }
    }

}