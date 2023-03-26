package com.game.curp.domain

class AddStateUseCase {
    fun invoque(string: String) : ResultCase {
        if(string.isEmpty()){
            return  ResultCase.ResultError(code = 1, error = "Favor de seleccionar un estado.")
        }
        return  ResultCase.ResultValid;
    }
}