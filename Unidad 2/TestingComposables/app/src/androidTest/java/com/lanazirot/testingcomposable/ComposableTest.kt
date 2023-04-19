package com.lanazirot.testingcomposable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
        composeTestRule.onNodeWithText("$text").assertIsDisplayed()
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

    @Test
    fun text_should_be_updated_when_add_button_is_pressed(){
        val text = "Counter:"
        composeTestRule.setContent {
            TestingComposableTheme {
                var count by remember { mutableStateOf(0) }
                Greeting(text, counter = count, onAdd = {
                    count+=it
                }, onSubtract = {
                    count-=it
                } )
            }
        }
        composeTestRule.onNodeWithTag("txt_name").assertExists("Didn't find txt_name tag")
        composeTestRule.onNodeWithTag("btn_add").performClick()
        composeTestRule.onNodeWithTag("btn_add").performClick()
        composeTestRule.onNodeWithTag("btn_add").performClick()
        composeTestRule.onAllNodesWithTag("btn_add")[0].performClick()

        composeTestRule.onNodeWithTag("txt_name").assertIsDisplayed().assert(!hasText("$text 6"))
        composeTestRule.onNodeWithTag("txt_name").assertIsDisplayed().assert(hasText("$text 4"))
    }

    @Test
    fun text_should_be_updated_when_substract_button_is_pressed(){
        val text = "Counter:"
        composeTestRule.setContent {
            TestingComposableTheme {
                var count by remember { mutableStateOf(4) }
                Greeting(text, counter = count, onAdd = {
                    count+=it
                }, onSubtract = {
                    count-=it
                } )
            }
        }

        composeTestRule.onNodeWithTag("txt_name").assertExists("Didn't find txt_name tag")
        composeTestRule.onNodeWithTag("btn_subtract").performClick()
        composeTestRule.onNodeWithTag("btn_subtract").performClick()
        composeTestRule.onNodeWithTag("btn_subtract").performClick()
        composeTestRule.onAllNodesWithTag("btn_subtract")[0].performClick()

        composeTestRule.onNodeWithTag("txt_name").assertIsDisplayed().assert(!hasText("$text 4"))
        composeTestRule.onNodeWithTag("txt_name").assertIsDisplayed().assert(hasText("$text 0"))
    }
}