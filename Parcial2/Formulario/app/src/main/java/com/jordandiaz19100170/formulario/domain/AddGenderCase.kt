package com.jordandiaz19100170.formulario.domain

class AddGenderCase {

    fun invoque(string: String) : ResultCase {

        if(string.isEmpty()){
            return  ResultCase.ResultError(code = 1, error = "No se selecciono el genero")
        }

        return  ResultCase.ResultValid;
    }
}