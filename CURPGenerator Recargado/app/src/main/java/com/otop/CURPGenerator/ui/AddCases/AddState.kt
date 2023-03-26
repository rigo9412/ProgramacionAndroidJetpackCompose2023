package com.otop.CURPGenerator.ui.AddCases

class AddState {
    fun invoque(string: String) : ResultCase {

        if(string.isEmpty()){
            return  ResultCase.ResultError(code = 1, error = "No se selecciono el estado")
        }

        return  ResultCase.ResultValid;
    }
}