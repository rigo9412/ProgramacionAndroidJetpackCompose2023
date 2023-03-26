package com.game.curp.domain

class AddGenderCase {
    fun invoque(string: String) : ResultCase {
        if(string.isEmpty()){
            return  ResultCase.ResultError(code = 1, error = "Favor de seleccionar un género")
        }
        return  ResultCase.ResultValid;
    }
}