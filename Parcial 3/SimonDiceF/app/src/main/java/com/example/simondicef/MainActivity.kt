package com.example.simondicef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simondicef.leaderboard.ui.LeaderboardViewModel
import com.example.simondicef.leaderboard.ui.UserViewModel
import com.example.simondicef.ui.simondice.ui.SimonDiceViewModel
import com.example.simondicef.ui.simondice.ui.simonDiceScreen
import com.example.simondicef.ui.theme.SimonDiceFTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimonDiceFTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var userVM : UserViewModel = hiltViewModel()
                    simonDiceScreen(viewModel = SimonDiceViewModel(), leaderboard = LeaderboardViewModel(),userVM)
                    //testVM()
                }
            }
        }
    }
}

//@Composable
//fun testVM(
//    viewmodel: UserViewModel = hiltViewModel()
//){
//    Column() {
//        Button(onClick = { viewmodel.getUsers() }) {
//            Text("CLICK ME")
//        }
//    }
//}