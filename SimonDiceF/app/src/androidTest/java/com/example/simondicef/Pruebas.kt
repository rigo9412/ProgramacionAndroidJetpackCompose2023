package com.example.simondicef

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.simondicef.leaderboard.ui.LeaderboardViewModel
import com.example.simondicef.ui.simondice.ui.SimonDiceViewModel
import com.example.simondicef.ui.simondice.ui.resultView
import com.example.simondicef.ui.simondice.ui.showLeaderboard
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class Pruebas {
    @get:Rule
    var rule = createComposeRule()

    private lateinit var simonDiceVM : SimonDiceViewModel
    private lateinit var lbVM: LeaderboardViewModel

    @Before
    fun init(){
        simonDiceVM = SimonDiceViewModel()
        lbVM = LeaderboardViewModel()
    }

    @Test
    fun test_empty_list(){
        rule.setContent {
            showLeaderboard(leaderboard = lbVM.top10.value)
        }

        rule.onNodeWithText("No hay ningun valor").assertExists()
    }

    @Test
    fun test_list_add_top(){
        rule.setContent {
            resultView(viewModel = lbVM, score = 1)
        }

        rule.onNodeWithTag("entry").performClick()
        rule.onNodeWithTag("field").assertExists()
    }
}