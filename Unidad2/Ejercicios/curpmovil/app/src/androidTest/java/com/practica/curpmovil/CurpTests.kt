package com.practica.curpmovil

import org.junit.Test
import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.practica.curpmovil.*
import com.practica.curpmovil.form.ui.FormScreen
import com.practica.curpmovil.form.ui.FormViewModel
import org.junit.Before
import org.junit.Rule


class CurpTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    //Test que confirme si al iniciar aparece un Text con el tag "texto_bienvenida"
    @Test
    fun cuando_inicie_la_aplicacion_debe_haber_un_texto_de_bienvenida_y_botones() {
        composeTestRule.setContent {
            var viewModel = FormViewModel()
            FormScreen(viewModel = viewModel)
        }
        composeTestRule.onNodeWithTag("texto_bienvenida").assertExists()
        composeTestRule.onNodeWithTag("boton_comenzar_forma_completa").assertExists()
        composeTestRule.onNodeWithTag("boton_comenzar_wizard").assertExists()

    }

    @Test
    fun cuando_inicie_la_app_presione_el_boton_de_forma_completa_y_debe_aparecer_el_date_picker_fecha_form_completo(){
        composeTestRule.setContent {
            var viewModel = FormViewModel()
            FormScreen(viewModel = viewModel)
        }
        composeTestRule.onNodeWithTag("boton_comenzar_forma_completa").performClick()
        composeTestRule.onNodeWithTag("fecha_form_completo").assertExists()
    }

    @Test
    fun cuando_incie_la_app_presione_el_boton_de_wizard_y_no_debe_aparecer_el_date_picker_fecha_wizard(){
        composeTestRule.setContent {
            var viewModel = FormViewModel()
            FormScreen(viewModel = viewModel)
        }
        composeTestRule.onNodeWithTag("boton_comenzar_wizard").performClick()
        composeTestRule.onNodeWithTag("fecha_wizard").assertDoesNotExist()
    }

}