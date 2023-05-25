package com.lanazirot.notificacionesexposicion.ui.screens.demo

import androidx.lifecycle.ViewModel
import com.lanazirot.notificacionesexposicion.application.interfaces.INotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DemoViewModel @Inject constructor(
    private val notificationService: INotificationService
) : ViewModel() {
    fun sendNotification() {
        notificationService.sendNotification()
    }
}