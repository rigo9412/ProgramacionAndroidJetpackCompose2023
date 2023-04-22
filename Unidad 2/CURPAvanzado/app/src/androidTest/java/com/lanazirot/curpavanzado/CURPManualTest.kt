package com.lanazirot.curpavanzado

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lanazirot.curpavanzado.provider.GlobalProvider
import com.lanazirot.curpavanzado.provider.LocalGlobalProvider
import com.lanazirot.curpavanzado.screens.viewmodels.WizardViewModel
import com.lanazirot.curpavanzado.ui.navgraph.AppNavigator
import com.lanazirot.curpavanzado.ui.theme.CURPAvanzadoTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CURPManualTest{

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init(){
        hiltRule.inject()

        composeTestRule.activity.setContent {
            val wizardVM = composeTestRule.activity.viewModels<WizardViewModel>().value
            val navController = rememberNavController()
            val gp = GlobalProvider(wizardVM = wizardVM, nav = navController)
            CURPAvanzadoTheme {
                CompositionLocalProvider(LocalGlobalProvider provides gp) {
                    Surface(
                        Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                    ) {
                        AppNavigator(globalProvider = gp)
                    }
                }
            }
        }

        composeTestRule.onNodeWithTag("manual_button").performClick()

    }

    @Test
    fun test_A_manual_mode_is_launched(){
        composeTestRule.onNodeWithTag("manual_mode_name").assert(hasText(""))
        composeTestRule.onNodeWithTag("manual_mode_lastname").assert(hasText(""))
        composeTestRule.onNodeWithTag("manual_mode_surname").assert(hasText(""))
        composeTestRule.onNodeWithTag("manual_mode_birthdate").assert(hasText(""))

    }

    @Test
    fun test_B_perform_text_inputs_to_check_if_they_are_correct_when_typing() {
        //Escribir un nombre en el campo de nombre y verificar que se haya escrito
        composeTestRule.onNodeWithTag("manual_mode_name").performTextInput("Juan")
        composeTestRule.onNodeWithTag("manual_mode_name").assert(hasText("Juan"))

        //Escribir un apellido en el campo de apellido y verificar que se haya escrito
        composeTestRule.onNodeWithTag("manual_mode_lastname").performTextInput("Perez")
        composeTestRule.onNodeWithTag("manual_mode_lastname").assert(hasText("Perez"))

        //Escribir un apellido en el campo de apellido y verificar que se haya escrito
        composeTestRule.onNodeWithTag("manual_mode_surname").performTextInput("Garcia")
        composeTestRule.onNodeWithTag("manual_mode_surname").assert(hasText("Garcia"))
    }

    @Test
    fun test_C_when_click_finish_button_should_not_be_able_to_provide_curp_since_form_state_is_wrong() {
        composeTestRule.onNodeWithTag("next_button").performClick()
        composeTestRule.onNodeWithTag("manual_mode_name").assertIsDisplayed()
        composeTestRule.onNodeWithTag("manual_mode_lastname").assertIsDisplayed()
        composeTestRule.onNodeWithTag("manual_mode_surname").assertIsDisplayed()
        composeTestRule.onNodeWithTag("manual_mode_birthdate").assertIsDisplayed()

    }
}