package com.curp.tests

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput

import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

import com.curp.ui.global.GlobalProvider
import com.curp.ui.global.GlobalStateScreenViewModel

import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.curp.MainActivity
import com.curp.components.CustomInput
import com.curp.components.DropdownStates
import com.curp.components.RadioButtonGroupSex
import com.curp.ui.form.items
import com.curp.ui.result.ui.CurpFormEvent
import com.curp.ui.result.ui.FormScreen
import com.curp.ui.result.ui.FormViewModel
import org.junit.Before

class TestForm{
    @get:Rule
    var rule = createComposeRule()

    @get:Rule
    var composeTestRule = createAndroidComposeRule<MainActivity>()
    private lateinit var counter: MutableState<Int>


    @Test
    fun when_value_needs_to_be_something() {
        rule.setContent {
            var a = mutableStateOf("RAFAEL")
            CustomInput(
                label = "Nombre",
                value = a.value,
                error = "",
                onChangeValue = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .testTag("txtNombre"),
                onAction = {}
            )
        }

        rule.onNodeWithTag("txtNombre")
            .performTextInput("RAFAEL")
        rule.onNodeWithText("RAFAEL", ignoreCase = true)
            .assertExists()
    }
    @Test
    fun when_is_error_in_custom_inputs(): Unit {
        var error = "El campo no puede estar vacio"
        rule.setContent {
            FormScreen()
        }
        rule.onNodeWithTag("done-button").performClick()

        rule.onNodeWithText(error).assertExists()
    }

    @get:Rule
    val composeTestRuleRB = createComposeRule()
    val composeTestRuleSGP = createComposeRule()

    @Test
    fun when_radio_button_sex_delicious_is_change() {
        var selectedItem: Pair<String, String>? = null
        composeTestRuleRB.setContent {
            RadioButtonGroupSex(
                modifier = Modifier.fillMaxSize(),
                items = items,
                selection = selectedItem?.first ?: "",
                onItemClick = { selectedItem = it }
            )
        }
        items.forEach { item ->
            composeTestRuleRB.onNodeWithText(text = item.second).performClick()
            assertEquals(item, selectedItem)
        }
    }

    @Test
    fun when_state_group_box_change() {
        val viewModel = composeTestRule.activity.viewModels<FormViewModel>().value
        var selected = ""
        composeTestRuleSGP.setContent {
            var data = viewModel.uiStateData.collectAsState().value
            DropdownStates(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .testTag("dpdStates"),
                selected = data.state,
                label = "Estado",
                listItems = data.statesList,
                onValueChange = {
                    selected = data.state.toString()
                    viewModel.onEvent(CurpFormEvent.StateChanged(it))
                }
            )
        }
        composeTestRuleSGP.onNodeWithTag("dpdStates").performClick()
        composeTestRuleSGP.onNodeWithText("Aguascalientes").performClick()
        composeTestRuleSGP.onNodeWithText(selected).assertExists()
    }
}