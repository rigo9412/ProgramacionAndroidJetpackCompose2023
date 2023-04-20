package com.lanazirot.curpavanzado

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lanazirot.curpavanzado.provider.GlobalProvider
import com.lanazirot.curpavanzado.provider.LocalGlobalProvider
import com.lanazirot.curpavanzado.screens.viewmodels.WizardViewModel
import com.lanazirot.curpavanzado.ui.navgraph.AppNavigator
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CURPManualTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var wizardVM: WizardViewModel

    @Before
    fun init(){
        composeTestRule.setContent {
            wizardVM = hiltViewModel()
            var navController = rememberNavController()
            val gp = GlobalProvider(wizardVM = wizardVM, nav = navController)
            /*CURPAvanzadoTheme {
                CompositionLocalProvider(LocalGlobalProvider provides gp) {
                    Surface(
                        Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                    ) {
                        AppNavigator(globalProvider = gp)
                    }
                }
            }*/
        }
    }

    @Test
    fun test_text_fields_not_empty() {
        // arrange

        // act
        // code to populate the fields with the above data and verify that the text is visible

        // assert
        // code to verify that the text is visible for each field
    }

    @Test
    fun test_date_field_is_read_only() {
        // arrange
        // code to find the date field

        // act
        // code to try to write to the date field

        // assert
        // code to verify that the date field is read-only
    }

    @Test
    fun test_gender_field_has_selected_value() {
        // arrange
        // code to find the gender field

        // act
        // code to select a gender value

        // assert
        // code to verify that a gender value is selected
    }

    @Test
    fun test_state_field_has_value() {
        // arrange
        // code to find the state field

        // act
        // code to select a state value

        // assert
        // code to verify that a state value is selected
    }

    @Test
    fun test_form_is_correct_and_vm_boolean_is_true() {
        // arrange
        // code to populate the form fields with valid data

        // act
        // code to submit the form

        // assert
        // code to verify that the form is correct and the VM boolean is true
    }

    @Test
    fun test_happy_path_wizard_state_is_valid() {
        // arrange
        // code to populate the form fields with valid data

        // act
        // code to submit the form

        // assert
        // code to verify that the wizard state is valid
    }
}