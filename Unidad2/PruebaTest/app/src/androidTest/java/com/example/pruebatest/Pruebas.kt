package com.example.pruebatest

import org.junit.Test
import org.junit.Before
import org.junit.Rule
import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule

class Test {

    @get:Rule
    var rule = createComposeRule()

    @Before
    fun init() {
        counter = mutableStateOf(0)
    }


    private lateinit var counter: MutableState<Int>

    @Test
    fun when_the_app_ready_then_show_message_hello_world() {
        val helloText = "HELLO WORLD"
        rule.setContent {

            Greeting(helloText)


        }
        rule.onNodeWithText(helloText).assertExists()
    }

    @Test
    fun when_the_app_ready_then_did_not_show_message_hello_world() {
        val helloText = ""
        val helloTextResult = "HELLO WORLD"
        rule.setContent {

            Greeting(helloText)
        }

        rule.onNodeWithText(helloTextResult).assertDoesNotExist()
    }

    @Test
    fun when_click_increment_button_then_show_the_number_positive_in_the_message() {
        val helloTextResult = "HELLO WORLD 4"
        val helloText = "HELLO WORLD"
        rule.setContent {

            ControlButton(clickAction =
            {
                counter.value += it
            })
            Greeting(name = "HELLO WORLD $counter")
        }

        rule.onNodeWithTag("button_add").performClick()
        rule.onNodeWithTag("button_add").performClick()
        rule.onNodeWithTag("button_add").performClick()
        rule.onAllNodesWithTag("button_add")[0].performClick()

        //Check result
        rule.onNodeWithText(helloTextResult).assertExists()
    }

    @Test
    fun when_click_decrement_button_then_show_the_number_negative_in_the_message() {
        val helloTextResult = "HELLO WORLD"
        rule.setContent {

            ControlButton(clickAction =
            {
                counter.value += it
            })
            Greeting(name = "HELLO WORLD $counter")
        }

        rule.onNodeWithTag("button_subtract").performClick()
        rule.onNodeWithTag("button_subtract").performClick()
        rule.onNodeWithTag("button_subtract").performClick()
        rule.onAllNodesWithTag("button_subtract")[0].performClick()
        rule.onAllNodesWithTag("button_subtract")[0].performClick()
        rule.onAllNodesWithTag("button_subtract")[0].performClick()

        //Check result
        rule.onNodeWithText(helloTextResult).assertExists()
    }
}