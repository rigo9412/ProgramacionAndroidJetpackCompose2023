package com.example.generadorcurp.domain

class AddBirthUseCase{
    fun invoque(string: String) : ResultCase{
        if(string.isEmpty()){
            return ResultCase.ResultError(code = 1, error = "Fecha de nacimiento vacia")
        }
        return ResultCase.ResultValid
    }
}