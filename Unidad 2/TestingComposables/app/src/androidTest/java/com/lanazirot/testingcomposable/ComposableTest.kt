package com.lanazirot.testingcomposable

import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lanazirot.testingcomposable.ui.theme.TestingComposableTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    private lateinit var count: MutableState<Int>

    @Before
    fun init(){
        count = mutableStateOf(0)
    }


    @Test
    fun text_should_be_placed_within_greeting_composable() {
        val text = "Android"
        composeTestRule.setContent {
            TestingComposableTheme {
                Greeting(text)
            }
        }
        composeTestRule.onNodeWithText("$text 0").assertIsDisplayed()
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

                Greeting(text, counter = count.value, onAdd = {
                    count.value+=it
                }, onSubtract = {
                    count.value-=it
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
            count.value = 4
            TestingComposableTheme {
                Greeting(text, counter = count.value, onAdd = {
                    count.value+=it
                }, onSubtract = {
                    count.value-=it
                } )
            }
        }


        composeTestRule.onNodeWithTag("txt_name").assertExists("Didn't find txt_name tag")
        composeTestRule.onNodeWithTag("btn_subtract").performClick()
        composeTestRule.onNodeWithTag("btn_subtract").performClick()
        composeTestRule.onNodeWithTag("btn_subtract").performClick()
        composeTestRule.onAllNodesWithTag("btn_subtract")[0].performClick()

        composeTestRule.onNodeWithTag("txt_name").assertIsDisplayed().assert(hasText("$text 0"))
    }
}