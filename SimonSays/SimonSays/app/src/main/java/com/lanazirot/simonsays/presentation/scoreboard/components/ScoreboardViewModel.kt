package com.lanazirot.simonsays.presentation.scoreboard.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreboardViewModel @Inject constructor(
    //Here repo
) : ViewModel() {

    var scoreboardList = emptyList<String>()

    init {
        //TODO Gather data from hander
        viewModelScope.launch {

        }
    }


}