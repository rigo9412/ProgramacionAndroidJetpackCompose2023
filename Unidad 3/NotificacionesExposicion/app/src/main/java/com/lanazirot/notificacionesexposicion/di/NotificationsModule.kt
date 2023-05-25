package com.lanazirot.notificacionesexposicion.di

import android.content.Context
import android.os.Build
import com.lanazirot.notificacionesexposicion.application.implementations.NotificationService
import com.lanazirot.notificacionesexposicion.application.interfaces.INotificationService
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