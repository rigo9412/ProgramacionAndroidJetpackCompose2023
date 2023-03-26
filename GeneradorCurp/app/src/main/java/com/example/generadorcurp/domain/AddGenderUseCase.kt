package com.example.generadorcurp.domain

class AddGenderUseCase {
    fun invoque(string: String) : ResultCase{
        if(string.isEmpty()){
            return ResultCase.ResultError(code = 1, error = "Seleccione un genero")
        }
        return ResultCase.ResultValid
    }
}