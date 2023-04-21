package com.lanazirot.curpavanzado

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.lanazirot.curpavanzado.domain.enums.State
import com.lanazirot.curpavanzado.screens.components.common.*
import org.junit.Rule
import org.junit.Test

class CURPComponentsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_curp_custom_alert_with_text_() {
        composeTestRule.setContent {
            CustomAlertDialog(
                message = "Test",
                showDialog = true,
                onDismiss = {}
            )
        }

        composeTestRule.onNodeWithText("Test").assertExists()
    }

    @Test
    fun test_curp_custom_input_without_error() {
        composeTestRule.setContent {
            CustomInput(
                value = "Sin errores",
                label = "Sin error",
                focusManager = LocalFocusManager.current,
                onValueChange = {},
                isError = false
            )
        }

        // Verificar que el componente se renderice correctamente
        composeTestRule.onNodeWithTag("outlinedTextField").assertIsDisplayed()

        //Verificar que el componente tenga el texto correcto
        composeTestRule.onNodeWithText("Sin error").assertExists()

        // Verificar que se pueda interactuar con el componente
        composeTestRule.onNodeWithTag("outlinedTextField").performClick().performTextInput("Texto de prueba")
    }

    @Test
    fun test_curp_custom_input_date_without_error() {
        composeTestRule.setContent {
            CustomInputDate(label = "Fecha", value = "2023-04-20")
        }

        // Verificar que el componente se renderice correctamente
        composeTestRule.onNodeWithTag("manual_mode_date_picker").assertIsDisplayed()

        //Verificar que el componente tenga el texto correcto
        composeTestRule.onNodeWithText("2023-04-20").assertExists()

        //Verificar que se le pueda dar click al componente
        composeTestRule.onNode(hasText("2023-04-20")).performClick()
    }

    @Test
    fun test_curp_custom_input_radio_button_selected() {
        composeTestRule.setContent {
            CustomInputRadioButton(
                text = "Testeo de radio button",
                selected = false,
                onClick = {}
            )
        }

        //Verificar que el componente se renderice correctamente
        composeTestRule.onNodeWithText("Testeo de radio button").assertExists()

        //Verificar que se le pueda dar click al componente
        composeTestRule.onNodeWithText("Testeo de radio button").performClick()
    }

    @Test
    fun test_curp_custom_input_select_selected() {

        composeTestRule.setContent {
            var selectedItem by remember { mutableStateOf(State.values()[0]) }

            CustomInputDropdownStates(
                value = selectedItem,
                onValueChange = {selectedItem = it},
                values = State.values().toList(),
                label = "Test"
            )
        }

        //Verificar que se pueda abrir el dialogo
        composeTestRule.onNodeWithTag("manual_mode_state").performClick()

        //Por cada estado, verificar que se renderice correctamente
        State.values().forEach { state ->
            composeTestRule.onNodeWithTag("manual_mode_state_${state.stateName}").assertExists()
        }

        //Verificar que se pueda seleccionar un estado
        composeTestRule.onNodeWithTag("manual_mode_state_${State.values()[5].stateName}").performClick()

        //Verificar que se haya colocado correctamente el texto del estado seleccionado
        composeTestRule.onNodeWithText(State.values()[5].stateName).assertExists()
    }
}