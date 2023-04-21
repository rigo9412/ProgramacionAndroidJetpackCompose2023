package com.jordandiaz19100170.formulario

import DatePickerBirthDate
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.jordandiaz19100170.formulario.components.CustomInput
import com.jordandiaz19100170.formulario.ui.form.ui.FormCurpScreenState
import com.jordandiaz19100170.formulario.ui.form.ui.FormScreen
import com.jordandiaz19100170.formulario.ui.form.ui.FormViewModel
import com.jordandiaz19100170.formulario.ui.global.GlobalProvider
import com.jordandiaz19100170.formulario.ui.global.GlobalStateScreenViewModel
import com.jordandiaz19100170.formulario.ui.nav.Navigator
import com.jordandiaz19100170.formulario.ui.wizard.ui.WizardViewModel

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CurpTest {
    @get:Rule
    var rule = createComposeRule()

    @get:Rule
    var composeTestRule = createAndroidComposeRule<MainActivity>()
    val testLabel = "test label"
    val testValue = "jordan"
    @Before
    fun init() {
        rule.setContent {
            var inputval by remember { mutableStateOf("") }
            CustomInput(
                label = testLabel,
                value = inputval,
                error = "",
                onChangeValue = { inputval = it },
                modifier = Modifier.testTag("inputtest"),
                onAction = { }
            )

        }

    }

    @Test
    fun when_the_inputfield_value_change() {
        rule.onNodeWithText(testLabel).performTextInput(testValue)
        rule.onNodeWithText(testValue).assertExists()

    }
    @Test
    fun if_the_inputfield_exist(){
        rule.onNodeWithText(testLabel).assertExists()
    }

    @Test
    fun test_if_the_formscreen_Inputs_exist(){
        composeTestRule.activity.setContent{

            val formVM = composeTestRule.activity.viewModels<FormViewModel>().value
            val wizardVM = composeTestRule.activity.viewModels<WizardViewModel>().value
            val globalVM = composeTestRule.activity.viewModels<GlobalStateScreenViewModel>().value

            val navController = rememberNavController()
            val gp = GlobalProvider(
                formVM = formVM,
                wizardVM = wizardVM,
                globalVM = globalVM,
                nav = navController)
            CompositionLocalProvider(GlobalProvider provides gp) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigator(gp.nav,gp.formVM,gp.wizardVM)
                }
            }
        }

        composeTestRule.onNodeWithTag("testname").assert(hasText(""))

        composeTestRule.onNodeWithTag("testmidname").assert(hasText(""))

        composeTestRule.onNodeWithTag("testlastmidname").assert(hasText(""))
    }

}
