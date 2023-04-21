package com.example.curp.ui.theme

class AddStateUseCase {
    fun invoque(string: String) : ResultCase {

        if(string.isEmpty()){
            return  ResultCase.ResultError(code = 1, error = "No se selecciono el estado")
        }

        return  ResultCase.ResultValid;
    }
}