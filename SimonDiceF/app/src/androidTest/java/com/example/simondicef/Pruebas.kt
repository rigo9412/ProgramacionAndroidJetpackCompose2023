package com.example.simondicef

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
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
    fun test_show_leaderboard_screen(){
        rule.setContent {
            showLeaderboard(leaderboard = lbVM.top10.value)
        }
        rule.onNodeWithTag("leaderboard").assertExists()
    }

    @Test
    fun test_show_empty_leaderboard(){
        rule.setContent {
            lbVM.changeJustShow(true)
            resultView(lbVM,0)
        }

        rule.onNodeWithText("No hay ningun valor").assertExists()
    }
    @Test
    fun test_add_user_to_leaderboard(){
        rule.setContent(){
            resultView(viewModel = lbVM, score = 1)
        }

        rule.onNodeWithTag("name").performTextInput("player")
        rule.onNodeWithTag("entry").performClick()

        rule.onNodeWithText("player").assertExists()
    }

    @Test
    fun test_add_user_to_leaderboard_top(){
        rule.setContent {
            resultView(viewModel = lbVM, score = 999)
        }
        rule.onNodeWithTag("name").performTextInput("Player 1")
        rule.onNodeWithTag("entry").performClick()

        rule.onAllNodesWithTag("player_name")[0].assert(hasText("Player 1"))
    }

    @Test
    fun test_add_user_fails_when_no_name_entry(){
        rule.setContent {
            resultView(viewModel = lbVM, score = 6)
        }

        rule.onNodeWithTag("entry").performClick()
        rule.onNodeWithTag("leaderboard").assertDoesNotExist()
    }
}