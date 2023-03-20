package com.example.registro

import android.annotation.SuppressLint
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
import com.example.registro.form.ui.FormScreen
import com.example.registro.form.ui.FormViewModel
import com.example.registro.ui.theme.RegistroTheme
import com.example.registro.result.ui.ResultScreen
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: FormViewModel by viewModels()
                RegistroTheme() {
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
    }}


