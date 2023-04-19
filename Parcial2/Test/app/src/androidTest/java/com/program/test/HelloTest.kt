package com.program.test

import androidx.compose.runtime.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test



class HelloTest {

    @get:Rule
    var rule = createComposeRule()

    private lateinit var counter: MutableState<Int>

    @Before
    fun init(){
        counter= mutableStateOf(0)
    }

            @Test
            fun when_The_App_Ready_Then_Show_Message_HelloWorld () {
                val helloText = "Hello World"
                rule.setContent {
                    Greeting(name = helloText) {}

                }
                rule.onNodeWithText(helloText).assertExists()

            }

            //Check


    @Test
    fun when_the_app_ready_then_did_not_show_message_hello_world(){
        val helloText = ""
        val helloTextResult = "Hello World"
        rule.setContent{
            Greeting(name = helloText) {}
        }
        rule.onNodeWithText(helloTextResult).assertDoesNotExist()

    }

    @Test
    fun when_Click_The_Increment_Button_Then_Show_The_Numbers_Positive_In_The_Message() {
        //prepare
        val helloTextResult = "hello world = 4"
        val helloText = "hello world"
        rule.setContent {
            var counter by remember { mutableStateOf(0) }
            Greeting(name = "$helloText = $counter") {
                counter += it
            }
        }

        //do something
        rule.onNodeWithTag("button_add").performClick()
        rule.onNodeWithTag("button_add").performClick()
        rule.onNodeWithTag("button_add").performClick()
        rule.onAllNodesWithTag("button_add")[0].performClick()

        //check result
        rule.onNodeWithText(helloTextResult).assertExists()
    }

    @Test
    fun when_click_reduce_button_then_show_the_numbers_negative_in_the_message(){

//prepare
        val helloTextResult = "hello world = -6"
        val helloText = "hello world"
        rule.setContent {
            var counter by remember { mutableStateOf(0) }
            Greeting(name = "$helloText = $counter") {
                counter += it
            }
        }

        //do something
        rule.onNodeWithTag("button_remove").performClick()
        rule.onNodeWithTag("button_remove").performClick()
        rule.onNodeWithTag("button_remove").performClick()
        rule.onAllNodesWithTag("button_remove")[0].performClick()
        rule.onAllNodesWithTag("button_remove")[0].performClick()
        rule.onAllNodesWithTag("button_remove")[0].performClick()

        //check result
        rule.onNodeWithText(helloTextResult).assertExists()

    }
}