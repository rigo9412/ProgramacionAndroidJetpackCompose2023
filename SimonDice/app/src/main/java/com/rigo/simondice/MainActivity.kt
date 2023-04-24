package com.rigo.simondice

import CustomButton
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import com.rigo.simondice.domain.models.Action
import com.rigo.simondice.domain.models.Game
import com.rigo.simondice.domain.models.Player
import com.rigo.simondice.ui.game.GameScreen
import com.rigo.simondice.ui.theme.*
import com.rigo.simondice.ui.top.TopScreenState
import com.rigo.simondice.ui.top.TopViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val topViewModel: TopViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            SimonDiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                                .fillMaxWidth()
                        )


                          TempTop(topViewModel)

                        CustomButton("INICIAR JUEGO", false, onClick = {
                            topViewModel.getTop()
                        })
//                        CustomButton("TOP", false, onClick = {})

                    }
                }
            }
        }
    }

    @Composable
    fun TempTop(topViewModel: TopViewModel){

        when( val state = topViewModel.uiState.collectAsState().value){
            is TopScreenState.Error -> Text(text = state.message,color= Color.White)
            TopScreenState.Loading -> Text(text = "cargando....",color= Color.White)
            is TopScreenState.Ready -> Text(text = "TOP = ${state.top.count()}",color= Color.White)
        }
    }
}







