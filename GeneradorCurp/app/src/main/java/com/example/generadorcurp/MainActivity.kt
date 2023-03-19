package com.example.generadorcurp

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.generadorcurp.form.ui.FormScreen
import com.example.generadorcurp.form.ui.components.FormViewModel
import com.example.generadorcurp.result.ResultadoScreen
import com.example.generadorcurp.ui.theme.GeneradorCurpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeneradorCurpTheme {
                val navController = rememberNavController()
                val viewModel: FormViewModel by viewModels()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    NavHost(navController = navController, startDestination = "formulario"){
                        composable("formulario"){ FormScreen(viewModel = viewModel, navigationController = navController)}
                        composable(
                            "result/{curp}",
                            arguments = listOf(
                                navArgument("curp"){type = NavType.StringType})
                        ){
                            val curp = it.arguments?.getString("curp")
                            ResultadoScreen(
                                curp ?: "curp",
                                navigationController = navController,
                                viewModel
                            )
                        }
                    }
                }
            }
        }
    }
}
