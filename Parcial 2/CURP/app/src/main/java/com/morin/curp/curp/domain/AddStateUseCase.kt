package com.morin.curp.curp.domain

class AddStateUseCase {
    fun invoque(string: String) : ResultCase {
        if(string.isEmpty()){
            return  ResultCase.ResultError(code = 1, error = "No se ha seleccionado el estado")
        }
        return  ResultCase.ResultValid;
    }
}