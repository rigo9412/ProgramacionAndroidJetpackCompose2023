package com.game.curp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class Testing {

    @get:Rule
    var rule = createComposeRule()

    @Before
    fun init() {

    }

    @Test
    fun whenTheAppReadyThenShowMessageHelloWorld() {
        val helloText = "Hello World"
        rule.setContent {

        }

        //do something

        //check result
        rule.onNodeWithText(helloText).assertExists()
    }
}