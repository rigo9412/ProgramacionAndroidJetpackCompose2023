package com.lanazirot.simonsays

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lanazirot.simonsays.domain.model.Score
import com.lanazirot.simonsays.domain.services.interfaces.IGameManager
import com.lanazirot.simonsays.presentation.pad.GameStatus
import com.lanazirot.simonsays.presentation.pad.PadViewModel
import com.lanazirot.simonsays.presentation.pad.components.CustomDialog
import com.lanazirot.simonsays.presentation.providers.GameProvider
import com.lanazirot.simonsays.presentation.providers.GlobalGameProvider
import com.lanazirot.simonsays.presentation.providers.GlobalProvider
import com.lanazirot.simonsays.presentation.providers.LocalGlobalProvider
import com.lanazirot.simonsays.presentation.scoreboard.components.ScoreBoardScreen
import com.lanazirot.simonsays.presentation.scoreboard.components.ScoreboardViewModel
import com.lanazirot.simonsays.ui.navgraph.AppNavGraph
import com.lanazirot.simonsays.ui.theme.SimonSaysTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ExamenTests {
    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_a_mostrar_pantalla_tops() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            val padViewModel = composeTestRule.activity.viewModels<PadViewModel>().value
            val gameViewModel = composeTestRule.activity.viewModels<ScoreboardViewModel>().value

            val navController = rememberNavController()
            val gp = GlobalProvider(padViewModel = padViewModel, nav = navController)
            val gameP = GameProvider(currentGame = gameViewModel)

            SimonSaysTheme {
                CompositionLocalProvider(LocalGlobalProvider provides gp) {
                    CompositionLocalProvider(GlobalGameProvider provides gameP) {
                        Surface(
                            Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                        ) {
                            AppNavGraph(globalProvider = gp)
                        }
                    }
                }
            }
        }

        val padViewModel = composeTestRule.activity.viewModels<PadViewModel>().value
        assert(padViewModel.pad.value.gameStatus == GameStatus.HOLD)
        val gameViewModel = composeTestRule.activity.viewModels<ScoreboardViewModel>().value
        gameViewModel.addToScoreLog(Score(100, "Test")) //Agregamos por defecto un valor

        //Nos movemos a la ventana de scores
        composeTestRule.onNodeWithTag("btn_scoreboard").performClick()

        //Verificar que se esta renderizando la pantalla
        composeTestRule.onNodeWithText("Top 10 Scores").assertExists()

        //Comprobar que se haya agregado el score
        composeTestRule.onNodeWithText("Test - 100 pts.").assertExists()

        //Cuando si tiene valores, este mensaje no debe estar mostrado
        composeTestRule.onNodeWithText("No hay scores por el momento.").assertDoesNotExist()

    }

    @Test
    fun test_b_mostrar_pantalla_sin_valores() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            val padViewModel = composeTestRule.activity.viewModels<PadViewModel>().value
            val gameViewModel = composeTestRule.activity.viewModels<ScoreboardViewModel>().value

            val navController = rememberNavController()
            val gp = GlobalProvider(padViewModel = padViewModel, nav = navController)
            val gameP = GameProvider(currentGame = gameViewModel)

            SimonSaysTheme {
                CompositionLocalProvider(LocalGlobalProvider provides gp) {
                    CompositionLocalProvider(GlobalGameProvider provides gameP) {
                        Surface(
                            Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                        ) {
                            AppNavGraph(globalProvider = gp)
                        }
                    }
                }
            }
        }
        val padViewModel = composeTestRule.activity.viewModels<PadViewModel>().value
        assert(padViewModel.pad.value.gameStatus == GameStatus.HOLD)

        //Nos movemos a la ventana de scores
        composeTestRule.onNodeWithTag("btn_scoreboard").performClick()

        //Cuando no tiene valores, debe de mostrar el texto de que no hay scores.
        composeTestRule.onNodeWithText("No hay scores por el momento.").assertExists()
    }

    @Test
    fun test_c_validar_insercion_de_registro() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            val padViewModel = composeTestRule.activity.viewModels<PadViewModel>().value
            val gameViewModel = composeTestRule.activity.viewModels<ScoreboardViewModel>().value

            val navController = rememberNavController()
            val gp = GlobalProvider(padViewModel = padViewModel, nav = navController)
            val gameP = GameProvider(currentGame = gameViewModel)

            SimonSaysTheme {
                CompositionLocalProvider(LocalGlobalProvider provides gp) {
                    CompositionLocalProvider(GlobalGameProvider provides gameP) {
                        Surface(
                            Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                        ) {
                            AppNavGraph(globalProvider = gp)
                        }
                    }
                }
            }
        }
        val padViewModel = composeTestRule.activity.viewModels<PadViewModel>().value
        assert(padViewModel.pad.value.gameStatus == GameStatus.HOLD)

        //Abrimos el dialogo para agregar un nuevo elemento, dejamos el nombre en blanco

        //Obtenemos el top 10 de scores antes de agregar el nuevo elemento

        //Damos click al boton de guardar, como el nombre esta en blanco, deberia de fallar

        //Obtenemos el top 10 de scores despues de agregar el nuevo elemento

        //Comparamos que el top 10 de scores antes y despues sea distinto (prueba fallida)


        //Cuando la insercion falla, debe de mostrar el mensaje de error
    }

    @Test
    fun test_d_validar_insercion_usuario_exitosa() {
        //Abrimos el dialogo para agregar un nuevo elemento
        composeTestRule.activity.setContent{
            var name by remember { mutableStateOf("") }
            val showDialog = remember { mutableStateOf(true) }

            if (showDialog.value) {
                CustomDialog(value = "", setShowDialog = {
                    showDialog.value = it
                }) {
                    name = it
                }
            }
        }
        //Ingresamos un nombre en el campo de texto
        composeTestRule.onNodeWithTag("player_name").performTextInput("ALAN")
        //Damos click al boton de guardar, como el nombre no es blanco, to do ok
        composeTestRule.onNodeWithTag("save_name").performClick()
        //No tiene que existir "dialogo"
        composeTestRule.onNodeWithTag("dialogo").assertDoesNotExist()
    }

    @Test
    fun test_e_validar_insercion_usuario_fallida() {
        composeTestRule.activity.setContent{
            var name by remember { mutableStateOf("") }
            val showDialog = remember { mutableStateOf(true) }

            if (showDialog.value) {
                CustomDialog(value = "", setShowDialog = {
                    showDialog.value = it
                }) {
                    name = it
                }
            }
        }
        //Damos click al boton de guardar, como el nombre no es blanco, to do ok
        composeTestRule.onNodeWithTag("save_name").performClick()
        //No tiene que existir "dialogo"
        composeTestRule.onNodeWithTag("dialogo").assertExists()
        composeTestRule.onNodeWithText("Necesitas ingresar un nombre").assertExists()
    }
}