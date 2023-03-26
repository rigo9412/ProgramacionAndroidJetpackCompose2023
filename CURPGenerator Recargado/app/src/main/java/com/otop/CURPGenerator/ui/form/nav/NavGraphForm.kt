package com.otop.CURPGenerator.ui.form.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.otop.CURPGenerator.ui.form.formui.FormScreen
import com.otop.CURPGenerator.ui.form.formui.FormViewModel
import com.otop.CURPGenerator.ui.form.result.ResultScreen

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
//            if (restart) {
//                formVM.initState()
//            }
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