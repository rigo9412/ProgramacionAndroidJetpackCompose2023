package com.lanazirot.testingcomposable

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lanazirot.testingcomposable.ui.theme.TestingComposableTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun text_should_be_placed_within_greeting_composable() {
        val text = "Android"
        composeTestRule.setContent {
            TestingComposableTheme {
                Greeting(text)
            }
        }
        composeTestRule.onNodeWithText("Hello $text!").assertIsDisplayed()
    }

    @Test
    fun text_should_not_be_placed_within_greeting_composable() {
        val text = "Android!"
        composeTestRule.setContent {
            TestingComposableTheme {
                Greeting(text)
            }
        }
        composeTestRule.onNodeWithText("Hello World!").assertDoesNotExist()
    }
}