package com.program.nuevocurp.domain

sealed class ResultCase(){
    object ResultValid: ResultCase()
    class ResultSuccess(val curp: String): ResultCase()
    class ResultError(val code: Int,val error: String): ResultCase()

}
