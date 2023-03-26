package com.jordandiaz19100170.formulario.domain

sealed class ResultCase(){
    object ResultValid: ResultCase()
    class ResultSuccess(val curp: String): ResultCase()
    class ResultError(val code: Int,val error: String): ResultCase()
}