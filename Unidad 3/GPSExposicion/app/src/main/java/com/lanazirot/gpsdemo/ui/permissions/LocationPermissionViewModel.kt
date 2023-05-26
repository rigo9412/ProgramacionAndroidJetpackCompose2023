package com.lanazirot.gpsdemo.ui.permissions

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//Creditos https://github.com/philipplackner/PermissionsGuideCompose/blob/master/app/src/main/java/com/plcoding/permissionsguidecompose/MainViewModel.kt

@HiltViewModel
class LocationPermissionViewModel @Inject constructor() : ViewModel() {
    val visiblePermissionDialogQueue = mutableStateListOf<String>()

    fun dismissDialog() {
        visiblePermissionDialogQueue.removeFirst()
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean,
    ) {
        if (!isGranted && !visiblePermissionDialogQueue.contains(permission)) {
            visiblePermissionDialogQueue.add(permission)
        }
    }
}