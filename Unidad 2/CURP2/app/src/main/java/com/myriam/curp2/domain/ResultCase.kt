package com.myriam.curp2.domain

sealed class ResultCase(){
    object ResultValid: ResultCase()
    class ResultSuccess(val curp: String): ResultCase()
    class ResultError(val code: Int,val error: String): ResultCase()
}