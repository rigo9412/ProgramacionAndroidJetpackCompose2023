package com.morin.curp.curp.domain

import com.morin.curp.curp.data.PATTERN_NAME

class AddNameUseCase {
    fun invoque(name: String): ResultCase {
        if(name.isEmpty()) {
            return ResultCase.ResultError(
                code = 1,
                error = "No debe estar vacío este campo"
            );
        }
        if (!name.matches(PATTERN_NAME)) {
            return  ResultCase.ResultError(
                code = 2,
                error = "No es valido este campo"
            );
        }
        return  ResultCase.ResultValid;
    }
}