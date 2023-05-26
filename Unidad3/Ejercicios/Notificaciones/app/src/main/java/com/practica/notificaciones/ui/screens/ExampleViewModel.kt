package com.practica.notificaciones.ui.screens

import androidx.lifecycle.ViewModel
import com.practica.notificaciones.notification.INotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val notificationService: INotificationService
) : ViewModel() {
    fun sendNotification() {
        notificationService.sendNotification()
    }
}