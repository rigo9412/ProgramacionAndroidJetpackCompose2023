package com.jordandiaz19100170.formulario.ui.nav

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.jordandiaz19100170.formulario.ui.form.ui.FormScreen
import com.jordandiaz19100170.formulario.ui.form.ui.FormViewModel
import com.jordandiaz19100170.formulario.ui.result.ui.ResultScreen



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