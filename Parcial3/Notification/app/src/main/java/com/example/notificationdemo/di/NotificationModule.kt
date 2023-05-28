package com.example.notificationdemo.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notificationdemo.MainActivity
import com.example.notificationdemo.R
import com.example.notificationdemo.receiver.MyReceiver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {



    @Singleton
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {
        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE
            else
                0


        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, flag)

        val pauseIntentAction = Intent(context, MyReceiver::class.java).apply {
            action = "Pause"
        }

        val pausePendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            pauseIntentAction,
            flag
        )

        val restartIntentAction = Intent(context, MyReceiver::class.java).apply {
            action = "Restart"
        }

        val restartPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            restartIntentAction,
            flag
        )

        val cancelIntentAction = Intent(context, MyReceiver::class.java).apply {
            action = "Cancel"
        }

        val cancelPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            cancelIntentAction,
            flag
        )

        return NotificationCompat.Builder(context, "Main Channel ID")
            .setContentTitle("Cronómetro en ejecución")
            .setContentText("Tiempo transcurrido: 00:00")
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .addAction(0, "Pausar", pausePendingIntent)
    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "Main Channel ID",
                "Main Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        return notificationManager
    }
}