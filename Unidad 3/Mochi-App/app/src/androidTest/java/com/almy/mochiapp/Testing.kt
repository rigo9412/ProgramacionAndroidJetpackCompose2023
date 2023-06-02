package com.almy.mochiapp

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.almy.mochiapp.screens.CreateTask.AssigmentScreen
import com.almy.mochiapp.screens.CreateTask.AssigmentViewModel
import com.almy.mochiapp.screens.LoginScreen.Login
import com.almy.mochiapp.screens.LoginScreen.LoginTest
import com.almy.mochiapp.screens.LoginScreen.LoginViewModel
import org.junit.Rule
import org.junit.Test

class Testing {
    @get:Rule
    var rule = createComposeRule()

    private val loginViewModel = LoginViewModel()
    private val assigmentViewModel = AssigmentViewModel()


    @Test
    fun verificarLoginInputs() {
        var email = "claudio@hotmail.com"

        rule.setContent {
            LoginTest(viewModel = loginViewModel)
        }

        //do something
        rule.onNodeWithTag("lblEmail").performTextInput("claudio@hotmail.com")

        //check result
        rule.onNodeWithText(email).assertExists()
    }

    @Test
    fun verifyLoginButtonClicks() {

        rule.setContent {
            LoginTest(viewModel = loginViewModel)
        }

        //do something
        rule.onNodeWithTag("btnCrearCuenta").performClick()
    }

    @Test
    fun verifyAutoAddCurrentMemberOnTaskCreation() {

        rule.setContent {
            AssigmentScreen(viewModel = assigmentViewModel)
        }

        //do something
        rule.onNodeWithTag("lblMember0").assertExists("claudio")
    }
}