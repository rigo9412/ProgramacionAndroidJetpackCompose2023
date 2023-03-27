package com.program.nuevocurp.domain

import com.program.nuevocurp.utils.PATTERN_NAME

class AddNameUseCase {
    fun invoque(name: String): ResultCase {

        if(name.isEmpty()) {
            return ResultCase.ResultError(
                code = 1,
                error = "El campo no puede estar vacío"
            );
        }

        if (!name.matches(PATTERN_NAME)) {
            return  ResultCase.ResultError(
                code = 2,
                error = "El valor capturado  no es valido"
            );
        }

        return  ResultCase.ResultValid;
    }
}