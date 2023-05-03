package com.game.simondicevm

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.game.simondicevm.ui.SimonViewModel
import com.game.simondicevm.ui.TopScreen
import com.game.simondicevm.ui.top
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class Testing {
    @get:Rule
    var rule = createComposeRule()

    private val viewModel = SimonViewModel()


    @Test
    fun mostrarPantallaDeTops() {

        rule.setContent {
            var n = 10
            while(n!=0)
            {
                viewModel.uiStateData.value.listaTop.add(top(nombre = "prueba$n", puntos = n))
                n--
            }
            TopScreen(viewModel = viewModel, listaTop = viewModel.uiStateData.value.listaTop)
        }

        //do something
        //rule.onNodeWithTag("top")

        //check result
        rule.onNodeWithTag("top1").assertExists()
    }

    @Test
    fun mostrarPantallaVaciaCuandoNoHayResultados() {

        rule.setContent {
            TopScreen(viewModel = viewModel, listaTop = viewModel.uiStateData.value.listaTop)
        }

        //check result
        rule.onNodeWithTag("estaVacio").assertExists()
    }

    @Test
    fun validarQueEnLaListaDeResultadosDelTopSeInserteElNuevoRegistroEnElTop() {

        rule.setContent {
            viewModel.uiStateData.value.listaTop.add(top("Claudio", 3))
            TopScreen(viewModel = viewModel, listaTop = viewModel.uiStateData.value.listaTop)
        }

        //check result
        rule.onNodeWithText("Claudio, 3 puntos.").assertExists()
    }

    @Test
    fun validarElRegistroDeUnNuevoUsuarioAlTopExitoso() {

        rule.setContent {
            var n = 10
            while(n!=0)
            {
                viewModel.uiStateData.value.listaTop.add(top(nombre = "prueba$n", puntos = n))
                n--
            }
            viewModel.uiStateData.value.listaTop.add(top("Claudio", 15))
            TopScreen(viewModel = viewModel, listaTop = viewModel.uiStateData.value.listaTop)
        }

        //check result
        rule.onNodeWithText("Claudio, 15 puntos.").assertExists()
    }

    @Test
    fun validarElRegistroDeUnNuevoUsuarioAlTopFallido() {

        rule.setContent {
            var n = 10
            while(n!=0)
            {
                viewModel.uiStateData.value.listaTop.add(top(nombre = "prueba$n", puntos = n))
                n--
            }
            viewModel.uiStateData.value.listaTop.add(top("Claudio", 0))
            TopScreen(viewModel = viewModel, listaTop = viewModel.uiStateData.value.listaTop)
        }

        //check result
        rule.onNodeWithText("Claudio, 0 puntos.").assertDoesNotExist()
    }
}