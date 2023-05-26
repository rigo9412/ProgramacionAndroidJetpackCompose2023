package com.lanazirot.gpsdemo.ui.screens.main

import com.google.maps.android.compose.MarkerState

data class MapScreenState(
    val markers: List<MarkerState> = emptyList()
)