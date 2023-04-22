package com.curp.ui.nav

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
    object StepNameScreen: Screens("StepNameScreen")
    object StepBirthScreen: Screens("StepBirthScreen")
    object StepGenderScreen: Screens("StepGenderScreen")
    object StepStateScreen: Screens("StepStateScreen")
    object HomeScreen: Screens("HomeScreen")

}