package com.otop.CURPGenerator

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.otop.CURPGenerator.form.ui.FormScreen
import com.otop.CURPGenerator.ui.theme.CURPGeneratorTheme
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: FormViewModel by viewModels()
            CURPGeneratorTheme {
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