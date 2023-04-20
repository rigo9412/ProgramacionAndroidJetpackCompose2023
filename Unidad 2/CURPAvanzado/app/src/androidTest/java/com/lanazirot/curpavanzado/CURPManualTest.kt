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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class CURPManualTest{

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun app_is_launched(){
        composeTestRule.activity.setContent {
            var wizardVM = composeTestRule.activity.viewModels<WizardViewModel>().value
            var navController = rememberNavController()
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
/*
    @Test
    fun test_text_fields_not_empty() {
        composeTestRule.onNodeWithTag("manual_button").performClick()
        composeTestRule.onNodeWithTag("manual_mode_name").assertTextEquals("")
        composeTestRule.onNodeWithTag("manual_mode_lastname").assertTextEquals("")
        composeTestRule.onNodeWithTag("manual_mode_surname").assertTextEquals("")
        composeTestRule.onNodeWithTag("manual_mode_birthdate").assertTextEquals("")
        composeTestRule.onNodeWithTag("manual_mode_gender_Hombre").assertIsSelected()
        composeTestRule.onNodeWithTag("manual_mode_state_Aguascalientes").assertIsSelected()
    }

    @Test
    fun test_date_field_is_read_only() {
    }

    @Test
    fun test_gender_field_has_selected_value() {
    }

    @Test
    fun test_state_field_has_value() {

    }

    @Test
    fun test_form_is_correct_and_vm_boolean_is_true() {

    }

    @Test
    fun test_happy_path_wizard_state_is_valid() {

    }*/
}