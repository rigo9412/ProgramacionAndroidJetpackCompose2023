package com.game.curp.domain

class AddBirthUseCase {
    fun invoque(string: String): ResultCase{
        if(string.isEmpty()){
            return ResultCase.ResultError(code = 1, error = "Favor de seleccionar la fecha de nacimiento.")
        }
        return ResultCase.ResultValid;
    }
}