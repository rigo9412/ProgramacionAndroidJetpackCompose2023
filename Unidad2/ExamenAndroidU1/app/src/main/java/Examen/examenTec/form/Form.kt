package Examen.examenTec.form

import androidx.lifecycle.ViewModel

class GatoGame : ViewModel (){

    fun ObtenerGanador(gameState: MutableList<MutableList<String>>): String {
        for (row in gameState) {
            if (row[0] != "" && row[0] == row[1] && row[1] == row[2]) {
                return row[0]
            }
        }

        for (col in 0..2) {
            if (gameState[0][col] != "" && gameState[0][col] == gameState[1][col] && gameState[1][col] == gameState[2][col]) {
                return gameState[0][col]
            }
        }

        if (gameState[0][0] != "" && gameState[0][0] == gameState[1][1] && gameState[1][1] == gameState[2][2]) {
            return gameState[0][0]
        }
        if (gameState[0][2] != "" && gameState[0][2] == gameState[1][1] && gameState[1][1] == gameState[2][0]) {
            return gameState[0][2]
        }

        for (row in gameState) {
            for (cell in row) {
                if (cell == "") {
                    return ""
                }
            }
        }
        return "Empate"
    }
}