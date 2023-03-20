package com.lanazirot.curpgenerator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lanazirot.curpgenerator.screens.Routes
import com.lanazirot.curpgenerator.screens.curp.components.CURPScreen
import com.lanazirot.curpgenerator.screens.loading.components.CURPLoadingScreen
import com.lanazirot.curpgenerator.screens.results.components.CURPResultScreen
import com.lanazirot.curpgenerator.ui.theme.CURPGeneratorTheme
import com.lanazirot.curpgenerator.ui.theme.Purple500

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.LOADING.route) {
                composable(Routes.CURP.route) {
                    CURPMainApp(navController = navController)
                }
                composable(Routes.LOADING.route) {
                    CURPLoadingScreen(
                        text = stringResource(id = R.string.app_loading),
                        navController = navController
                    )
                }
                composable(Routes.RESULT().route) {
                    CURPResultScreen(
                        curp = it.arguments?.getString("curp") ?: "CURP",
                        name = it.arguments?.getString("name") ?: "Nombre"
                    ) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CURPMainApp(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CURP Generador", color = Color.White) }, backgroundColor = Color(
                    Purple500.value
                )
            )
        },
        content = {
            CURPScreen(navController)
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CURPGeneratorTheme {
        CURPMainApp(navController = rememberNavController())
    }
}