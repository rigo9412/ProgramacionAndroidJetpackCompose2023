package com.curp.ui.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.curp.ui.result.ui.FormScreen
import com.curp.ui.result.ui.FormViewModel
import com.curp.ui.result.ui.ResultScreen



@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.NavGraphForm(formVM: FormViewModel){

    navigation(
        startDestination = Screens.Form.route,
        route = RoutesGraph.FORM.toString()
    ){
        composable(
            Screens.Form.route,
            arguments = listOf(
                navArgument("restart") {
                    type = NavType.BoolType
                },
            )
        ) {
            val restart = it.arguments?.getBoolean("restart", false)!!
            FormScreen()
        }

        composable(
            Screens.Result.route,
            arguments = listOf(
                navArgument("curp") {
                    type = NavType.StringType
                },
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "name"
                },
                navArgument("lastname") {
                    type = NavType.StringType
                    defaultValue = "lastname"
                }
            )

        ) {
            val curp = it.arguments?.getString("curp", "curp")!!
            val name = it.arguments?.getString("name")!!
            val lastname = it.arguments?.getString("name")!!
            ResultScreen(curp, name + lastname)
        }
    }
}