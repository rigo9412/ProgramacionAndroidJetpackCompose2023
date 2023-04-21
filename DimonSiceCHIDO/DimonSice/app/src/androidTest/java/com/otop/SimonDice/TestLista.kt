package com.otop.SimonDice

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.otop.SimonDice.ui.MainScreen
import com.otop.SimonDice.ui.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TestLista {

    @get:Rule
    var rule = createComposeRule()

    @get:Rule
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun when_top_is_update(): Unit {

        composeTestRule.activity.setContent {
            val formVM = composeTestRule.activity.viewModels<MainViewModel>().value
            formVM.gameOver.value = true
            formVM.topName.value = "Pedro"
            formVM.level.value = 2
            MainScreen(viewModel = formVM)
        }
        composeTestRule.onNodeWithTag("btnUpdate").performClick()
        composeTestRule.onNodeWithTag("txtNombre").assertExists()
    }

    @Test
    fun when_top_is_user_success(): Unit {

        composeTestRule.activity.setContent {
            val formVM = composeTestRule.activity.viewModels<MainViewModel>().value
            formVM.gameOver.value = true
            formVM.topName.value = "Pedro"
            formVM.level.value =10
            MainScreen(viewModel = formVM)
        }
        composeTestRule.onNodeWithTag("btnUpdate").performClick()
        composeTestRule.onNodeWithTag("txtNombre").assertExists()
    }

    @Test
    fun when_top_user_is_failed(): Unit {
        val formVM = composeTestRule.activity.viewModels<MainViewModel>().value
        composeTestRule.activity.setContent {

            formVM.gameOver.value = true
            formVM.topName.value = "Pedro"
            formVM.level.value = 0
            MainScreen(viewModel = formVM)
        }
        composeTestRule.onNodeWithTag("btnUpdate").performClick()
        composeTestRule.onNodeWithTag("txtNombre").assertExists()
    }
}