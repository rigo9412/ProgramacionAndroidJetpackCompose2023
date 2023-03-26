package com.example.generadorcurp.form.domain.ui

data class CurpFormModelState(
    val nombre: String = "",
    val nombreError: String? = null,
    val paterno: String = "",
    val paternoError: String? = null,
    val materno: String = "",
    val maternoError: String? = null,
    val fechaNacimiento: String = "",
    val fechaNacimientoError: String? = null,
    val genero: Pair<String,String> = Pair<String,String>("",""),
    var generoError: String? = null,
    val estado: Pair<String,String> = Pair<String,String>("",""),
    val estadoError: String? = null,
    val generoList: ArrayList<Pair<String,String>>  = ArrayList(),
    val estadoList: ArrayList<Pair<String,String>> = ArrayList(),
)