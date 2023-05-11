package com.lanazirot.simonsays.domain.model.enums

sealed class AppStatus {
    object RUNNING : AppStatus()
    class NOTIFICATION(var value: Any) : AppStatus()
}