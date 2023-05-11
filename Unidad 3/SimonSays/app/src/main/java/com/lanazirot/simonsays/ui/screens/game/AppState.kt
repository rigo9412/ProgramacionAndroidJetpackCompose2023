package com.lanazirot.simonsays.ui.screens.game

import com.lanazirot.simonsays.domain.model.enums.AppStatus

data class AppState(val status: AppStatus = AppStatus.RUNNING)