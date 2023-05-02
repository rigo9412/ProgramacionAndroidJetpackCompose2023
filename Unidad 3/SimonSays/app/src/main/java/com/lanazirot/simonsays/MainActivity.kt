package com.lanazirot.simonsays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.lanazirot.simonsays.ui.common.components.ui.pad.PadViewModel
import com.lanazirot.simonsays.ui.providers.GameProvider
import com.lanazirot.simonsays.ui.providers.GlobalGameProvider
import com.lanazirot.simonsays.ui.providers.GlobalProvider
import com.lanazirot.simonsays.ui.providers.LocalGlobalProvider
import com.lanazirot.simonsays.ui.screens.scoreboard.ScoreboardViewModel
import com.lanazirot.simonsays.ui.navgraph.AppNavGraph
import com.lanazirot.simonsays.ui.theme.SimonSaysTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val padViewModel: PadViewModel by viewModels()
    private val gameViewModel: ScoreboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var navController = rememberNavController()
            val gp = GlobalProvider(padViewModel = padViewModel, nav = navController)
            val gameP = GameProvider(currentGame = gameViewModel)

            SimonSaysTheme {
                CompositionLocalProvider(LocalGlobalProvider provides gp) {
                    Surface(
                        modifier = Modifier.fillMaxSize(), color = Color.Black
                    ) {
                        CompositionLocalProvider(GlobalGameProvider provides gameP) {
                            Surface(
                                modifier = Modifier.fillMaxSize(), color = Color.Black
                            ) {
                                AppNavGraph(globalProvider = gp)
                            }
                        }
                    }
                }
            }
        }
    }
}

