package Examen.examenTec

import Examen.examenTec.form.Fin
import Examen.examenTec.form.Gato
import Examen.examenTec.form.GatoGame
import Examen.examenTec.form.Iniciar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import Examen.examenTec.ui.theme.ExamenTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ExamenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var tabla by remember {mutableStateOf(mutableListOf(mutableListOf("","",""),mutableListOf("","",""),mutableListOf<String>("","","")))}
                    var jugador by remember { mutableStateOf( true)}
                    var ganador by remember { mutableStateOf("")}

                    var Gato = GatoGame()

                    Iniciar()
                    Gato(tabla, jugador) {
                        jugador = !jugador
                        ganador = Gato.ObtenerGanador(tabla)
                    }
                    if (ganador != ""){
                        Fin(ganador)
                        tabla = mutableListOf(mutableListOf("","",""),mutableListOf("","",""),mutableListOf("","",""))
                        jugador = true
                    }
                }
            }
        }
    }
}
