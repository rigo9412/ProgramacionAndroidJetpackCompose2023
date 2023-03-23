package com.example.curpregistro

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
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.*
import com.example.curpregistro.result.ui.ResultScreen
import com.example.curpregistro.ui.FormScreen
import com.example.curpregistro.ui.FormViewModel
import com.example.curpregistro.ui.theme.CurpRegistroTheme



class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurpRegistroTheme {
                val navController = rememberNavController()
                val viewModel: FormViewModel by viewModels()
           //     val viewModelWizard: WizardViewModel by viewModels()
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
