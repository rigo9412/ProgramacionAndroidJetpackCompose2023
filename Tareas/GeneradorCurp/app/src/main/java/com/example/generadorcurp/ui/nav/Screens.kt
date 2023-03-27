package com.example.generadorcurp.ui.nav

sealed class Screens (val route:String){
    object Form: Screens("form?restart={restart}"){
        fun generateRoute(restart: Boolean?) : String{
            return "form?restart=${restart}"
        }
    }
    object Result: Screens("result?curp={curp}&name={name}&lastname={lastname}") {
        fun generateRoute(curp: String,name: String) : String{
            return "result?curp=${curp}&name=${name}&lastname=${name}"
        }
    }
    object StepInstructionsScreen: Screens("StepInstructionScreen?restart={restart}"){
        fun generateRoute(restart: Boolean?) : String{
            return "StepInstructionScreen?restart=${restart}"
        }
    }
    object StepNombreScreen: Screens("StepNombreScreen")
    object StepFechaScreen: Screens("StepFechaScreen")
    object StepGeneroScreen: Screens("StepGeneroScreen")
    object StepEstadoScreen: Screens("StepEstadoScreen")
    object HomeScreen: Screens("HomeScreen")

}