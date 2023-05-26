package com.practica.notificaciones.di

import android.content.Context
import com.practica.notificaciones.notification.INotificationService
import com.practica.notificaciones.notification.NotificationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationsModule {

    @Provides
    @Singleton
    fun provideNotificationService(@ApplicationContext context: Context): INotificationService {
        return NotificationService(context = context)
    }
}