package tec.mx.curp

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class TestCurp {

    @get:Rule
    var rule = createComposeRule()

    @Test
    fun when_the_app_ready_then_show_message_hello_world() {
        val helloText = "HELLO ANDROID"
        rule.setContent {
            Greeting()
        }
        rule.onNodeWithText(helloText).assertExists()
    }

    @Composable
    private fun Greeting() {
        Text(text = "HELLO ANDROID")
    }
}