package com.example.generadorcurp.domain

import com.example.generadorcurp.form.domain.PATTERN_NAME

class AddNameUseCase{
    fun invoque(string: String) : ResultCase{
        if(string.isEmpty()){
            return ResultCase.ResultError(code = 1, error = "Campo no puede estar vacio")
        }
        if(!string.matches(PATTERN_NAME)){
            return ResultCase.ResultError(code = 2, error = "Valor no valido")
        }
        return ResultCase.ResultValid
    }
}