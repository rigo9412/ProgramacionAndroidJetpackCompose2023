package com.curp

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
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.curp.form.ui.FormScreen
import com.curp.form.ui.FormViewModel
import com.curp.result.ResultScreen
import com.curp.ui.theme.CURPTheme

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: FormViewModel by viewModels()
            CURPTheme {
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