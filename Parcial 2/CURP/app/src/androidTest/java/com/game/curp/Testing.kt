package com.game.curp

import android.view.FrameMetrics.ANIMATION_DURATION
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.game.curp.forms.ui.Form
import com.game.curp.forms.ui.FormViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class Testing {

    @get:Rule
    var rule = createComposeRule()

    private val viewModel = FormViewModel()

    @Before
    fun init() {
        rule.setContent {
            Form(viewModel = viewModel)
        }
    }

    @Test
    fun verificarTextoInput() {
        var nombre = "CLAUDIO"

        //do something
        rule.onNodeWithTag("txtNombre").performTextInput("claudio")

        //check result
        rule.onNodeWithText(nombre).assertExists()
    }

    @Test
    fun verificarSiSeleccionaSexoFemenino() {
        /*rule.setContent {
            Form(viewModel = viewModel)
        }*/

        //do something
        rule.onNodeWithTag("radMujer").performClick()

        //check result
        rule.onNodeWithTag("radMujer").assertIsEnabled()
    }

    @Test
    fun verificarSiSeleccionoEstado() {
        /*rule.setContent {
            Form(viewModel = viewModel)
        }*/

        //do something
        rule.onNodeWithTag("cajita").performClick()
        //rule.onNodeWithTag("listaCajita").performTouchInput { swipeDown() }
        rule.onNodeWithTag("listaCajita").performTouchInput { click(center) }

        //check result
        rule.onNodeWithTag("txtEstado").assertTextContains(viewModel.uiStateData.value.state.second)
    }

    @Test
    fun verificarSiNoGeneroUnCURP() {
        /*rule.setContent {
            Form(viewModel = viewModel)
        }*/

        //do something
        rule.onNodeWithTag("btnGenerarCURP").performClick()

        //check result
        rule.onNodeWithTag("txtCURP").assertTextContains("")
    }
}