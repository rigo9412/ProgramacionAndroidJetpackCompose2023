package com.example.registrocurp.curp.domain

class AddBirthUseCase {
    fun invoque(string: String) : ResultCase {

        if(string.isEmpty()){
            return ResultCase.ResultError(
                code = 1,
                error = "No se selecciono la fecha de nacimiento"
            )
        }

        return ResultCase.ResultValid;
    }
}