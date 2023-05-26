package com.lanazirot.gpsdemo.ui.permissions

class LocationPermission: PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "Location permission is needed to show nearby places. Go to Settings to enable it."
        } else {
            "Location permission is needed to show nearby places."
        }
    }
}

class NotificationPermission: PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "Notification permission is needed to show nearby places. Go to Settings to enable it."
        } else {
            "Notification permission is needed to show nearby places."
        }
    }
}

class BackgroundLocationPermission: PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "Background location permission is needed to show nearby places. Go to Settings to enable it."
        } else {
            "Background location permission is needed to show nearby places."
        }
    }
}