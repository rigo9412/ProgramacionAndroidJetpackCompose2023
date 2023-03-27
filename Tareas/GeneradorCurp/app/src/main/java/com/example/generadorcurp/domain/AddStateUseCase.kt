package com.example.generadorcurp.domain

class AddStateUseCase{
    fun invoque(string: String) : ResultCase{
        if(string.isEmpty()){
            return ResultCase.ResultError(code = 1, error = "Seleccione un estado")
        }
        return ResultCase.ResultValid
    }
}