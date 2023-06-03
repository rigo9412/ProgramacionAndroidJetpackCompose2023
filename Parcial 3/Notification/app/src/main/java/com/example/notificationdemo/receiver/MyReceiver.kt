package com.example.notificationdemo.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notificationdemo.NotificationViewModel
import com.example.notificationdemo.cronometro
import javax.inject.Inject

class MyReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action)
        {
            "Pause" -> cronometro.pausarCronometro()
        }
    }

}