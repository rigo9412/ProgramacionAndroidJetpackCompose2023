package com.game.curp.domain

class AddNameUseCase {
    fun invoque(name: String): ResultCase {
        if(name.isEmpty()) {
            return ResultCase.ResultError(
                code = 1,
                error = "No dejar campos vacíos."
            );
        }
        if (!name.matches(PATTERN_NAME)) {
            return  ResultCase.ResultError(
                code = 2,
                error = "Datos inválidos."
            );
        }
        return  ResultCase.ResultValid;
    }
}