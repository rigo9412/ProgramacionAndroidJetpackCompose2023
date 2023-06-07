package com.program.nivelacion

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.program.nivelacion.ui.data.MainViewModel
import com.program.nivelacion.ui.pantallas.ControlDePantallas
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class Testing {

    @get:Rule
    val TestRule = createComposeRule()

    @Before
    fun init() {

        //val viewModel = MainViewModel()
        TestRule.setContent {
            //Pantall Form(viewModel = viewModel)
        }
    }
    /*@Test
    fun Test_probar_Seleccionar_Las_2_primeras_casillas(){

        composeTestRule.setContent { ScreenJuego(viewModel = viewModel) }

        composeTestRule.onNodeWithTag("1").performClick()

        composeTestRule.onNodeWithTag("BoxActual").assertTextContains("1")
        Thread.sleep(2000)
    }*/
}