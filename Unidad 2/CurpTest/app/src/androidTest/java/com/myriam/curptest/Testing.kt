package com.myriam.curptest

import android.view.FrameMetrics.ANIMATION_DURATION
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.myriam.curptest.forms.ui.Form
import com.myriam.curptest.forms.ui.FormViewModel
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
        var nombre = "MYRIAM"

        //do something
        rule.onNodeWithTag("txtNombre").performTextInput("MYRIAM")

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




}