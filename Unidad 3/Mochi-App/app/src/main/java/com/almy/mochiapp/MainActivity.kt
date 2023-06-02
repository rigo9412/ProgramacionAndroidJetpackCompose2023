package com.almy.mochiapp

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.almy.mochiapp.firebase.auth
import com.almy.mochiapp.firebase.isLoggedIn
import com.almy.mochiapp.notify.listenPostAssigments
import com.almy.mochiapp.screens.AssigmentsCompletedScreen.AssigmentsCompletedScreen
import com.almy.mochiapp.screens.AssigmentsCompletedScreen.AssigmentsCompletedViewModel
import com.almy.mochiapp.screens.AssigmentsDetails.OnScreenTarea
import com.almy.mochiapp.screens.AssigmentsDetails.ScreenTarea
import com.almy.mochiapp.screens.CreateTask.AssigmentViewModel
import com.almy.mochiapp.screens.CreateTask.CreateTaskScreen
import com.almy.mochiapp.screens.MainSreen.MainScreen
import com.almy.mochiapp.screens.MainSreen.MainViewModel
import com.almy.mochiapp.screens.MainSreen.OnMainScreen
import com.almy.mochiapp.screens.MenuSplashScreen.MenuSplashScreen
import com.almy.mochiapp.screens.MenuSplashScreen.MenuSplashViewModel
import com.almy.mochiapp.ui.theme.MochiAppTheme
import com.almy.mochiapp.ui.theme.LightPurple
import com.myriam.pantallalateral.AllScreens
import java.util.*

class MainActivity : ComponentActivity() {

    private fun requestPermissions(vararg permissions: String) {
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            result.entries.forEach {
                Log.d("MainActivity", "${it.key} = ${it.value}")
            }
        }
        requestPermissionLauncher.launch(permissions.asList().toTypedArray())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions(Manifest.permission.POST_NOTIFICATIONS)

        //codigo para poner manualmente la app en ingles
        /*val config = resources.configuration
        val locale = Locale("en")
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            config.setLocale(locale)
        else
            config.locale = locale

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)*/

        setContent {
            val menuSplashViewModel: MenuSplashViewModel by viewModels()
            val navControllerLogin = rememberNavController()

            MochiAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(),
                    color = LightPurple
                ) {
                    NavigationGraph1(
                        navController = navControllerLogin,
                        menuSplashViewModel = menuSplashViewModel,
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        listenPostAssigments(applicationContext)
    }
}


@Composable
fun NavigationGraph1(
    navController: NavHostController,
    menuSplashViewModel: MenuSplashViewModel
) {

    NavHost(navController, startDestination = if (isLoggedIn()) "pantallas" else "login") {
        composable("login") {
            MenuSplashScreen(viewModel = menuSplashViewModel, navController)
        }
        composable("pantallas"){
            AllScreens(
                navControllerLogin = navController,
            )
        }
    }
}



@Composable
fun NavigationGraph2(
    navController: NavHostController,
    navControllerLogin: NavController
) {
    val assigmentViewModel: AssigmentViewModel = viewModel()
    val mainViewModel: MainViewModel = viewModel()
    val assigmentsCompletedViewModel : AssigmentsCompletedViewModel = viewModel()
    NavHost(navController, startDestination = "inicio") {
        composable("inicio"){
            OnMainScreen(navController, mainViewModel, navControllerLogin)
            //MainScreen(navController, mainViewModel, navControllerLogin)
        }
        composable("usuario") {
        }
        composable("tareascompletadas") {
            AssigmentsCompletedScreen(viewModel = assigmentsCompletedViewModel, navController)
        }
        composable("crearnotas"){
            CreateTaskScreen(assigmentViewModel)
        }
        composable(
            "vertarea/{assigmentID}",
            arguments = listOf(navArgument("assigmentID") { type = NavType.StringType})
        ){
            OnScreenTarea(it.arguments?.getString("assigmentID") ?: "", mainViewModel)
        }
    }
}
