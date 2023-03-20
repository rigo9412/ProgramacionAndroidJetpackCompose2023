package com.jordandiaz19100170.formulario

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import com.jordandiaz19100170.formulario.result.ui.ResultScreen
import com.jordandiaz19100170.formulario.ui.FormScreen
import com.jordandiaz19100170.formulario.ui.FormViewModel
import com.jordandiaz19100170.formulario.ui.theme.FormularioTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: FormViewModel by viewModels()
            FormularioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = "form") {
                        composable("form") { FormScreen(viewModel, navController) }

                        composable(
                            "result/{curp}/{name}",
                            arguments = listOf(
                                navArgument("curp") {
                                    type = NavType.StringType
                                },
                                navArgument("name") {
                                    type = NavType.StringType
                                })
                        ) {
                            val curp = it.arguments?.getString("curp")
                            val name = it.arguments?.getString("name")
                            ResultScreen(curp ?: "CURP", name ?: "name", navController)
                        }
                    }
                }
            }
        }
    }
}