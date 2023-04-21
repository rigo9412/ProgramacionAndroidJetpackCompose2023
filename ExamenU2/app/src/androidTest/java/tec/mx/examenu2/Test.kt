package tec.mx.examenu2

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import tec.mx.examenu2.simonDiceUI.HighScoresList
import tec.mx.examenu2.simonDiceUI.Score
import tec.mx.examenu2.simonDiceUI.SimonDiceViewModel


class SimonDiceTest {

    @get:Rule
    var rule = createComposeRule()

    @Test
    fun validar_que_en_la_lista_se_inserte_un_registro_en_el_top() {
        val viewModel = SimonDiceViewModel()
        val expectedScores = mutableListOf(
            Score("Player 3", 300),
            Score("Player 2", 200),
            Score("Player 4", 150),
            Score("Player 1", 100)
        )
        rule.setContent {
            viewModel.addScore("Player 1", 100)
            viewModel.addScore("Player 2", 200)
            viewModel.addScore("Player 3", 300)
            viewModel.addScore("Player 4", 150)
        }
        assertEquals(expectedScores, viewModel._highScores)
    }

    @Test
    fun validar_el_registro_de_un_top_exitoso() {
        val viewModel = SimonDiceViewModel()
        val expectedScores = mutableListOf(
            Score("Player 4", 550),
            Score("Player 3", 300),
            Score("Player 2", 200),
            Score("Player 1", 100)
        )
        rule.setContent {
            viewModel.addScoreWithUserName("Player 1", 100)
            viewModel.addScoreWithUserName("Player 2", 200)
            viewModel.addScoreWithUserName("Player 3", 300)
            viewModel.addScoreWithUserName("Player 4", 550)
        }
        assertEquals(expectedScores, viewModel._highScores)
    }

    @Test
    fun validar_el_registro_de_un_top_fallido() {
        val viewModel = SimonDiceViewModel()
        val expectedScores = mutableListOf(
            Score("Player 1", 1000),
            Score("Player 2", 900),
            Score("Player 3", 800),
            Score("Player 4", 700),
            Score("Player 5", 600),
            Score("Player 6", 500),
            Score("Player 7", 400),
            Score("Player 8", 300),
            Score("Player 9", 200),
            Score("Player 10", 100)
        )
        rule.setContent {
            viewModel.addScore("Player 1", 1000)
            viewModel.addScore("Player 2", 900)
            viewModel.addScore("Player 3", 800)
            viewModel.addScore("Player 4", 700)
            viewModel.addScore("Player 5", 600)
            viewModel.addScore("Player 6", 500)
            viewModel.addScore("Player 7", 400)
            viewModel.addScore("Player 8", 300)
            viewModel.addScore("Player 9", 200)
            viewModel.addScore("Player 10", 100)
            viewModel.addScore("Player 11", 50)
        }

        assertEquals(expectedScores, viewModel._highScores)
    }
}




