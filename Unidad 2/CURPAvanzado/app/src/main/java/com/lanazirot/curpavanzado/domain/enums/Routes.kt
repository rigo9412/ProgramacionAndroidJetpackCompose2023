package com.lanazirot.curpavanzado.domain.enums

sealed class Routes {
    object Welcome {
        const val route = "welcome"
    }
    object Manual{
        const val route = "manual"
    }
    object WizardName {
        const val route = "wizard/name"
    }
    object WizardBirthDate {
        const val route = "wizard/birthdate"
    }
    object WizardGender{
        const val route = "wizard/gender"
    }
    object WizardState{
        const val route = "wizard/state"
    }
    object WizardResult{
        const val route = "wizard/result?name={name}?lastname={lastname}?curp={curp}"
        fun getRoute(name: String, lastname: String, curp: String) = "wizard/result?name=$name?lastname=$lastname?curp=$curp"
    }
}