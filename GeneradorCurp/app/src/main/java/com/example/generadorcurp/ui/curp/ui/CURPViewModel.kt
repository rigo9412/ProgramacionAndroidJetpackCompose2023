package com.example.generadorcurp.ui.curp.ui

import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.util.Date

class CURPViewModel : ViewModel() {
    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _primerApellido = MutableLiveData<String>()
    val primerApellido: LiveData<String> = _primerApellido

    private val _segundoApellido = MutableLiveData<String>()
    val segundoApellido: LiveData<String> = _segundoApellido

    private val _estado = MutableLiveData<Pair<String,String>>()
    val estado: LiveData<Pair<String,String>> = _estado

    private val _sexo = MutableLiveData<String>()
    val sexo: LiveData<String> = _sexo

    private val _fecha = MutableLiveData<LocalDate>()
    val fecha: LiveData<LocalDate> = _fecha

    private val _showCurp = MutableLiveData<Boolean>()
    val showCurp: LiveData<Boolean> = _showCurp

    fun onNameChange(nombre: String, pApellido: String, sApellido: String) {
        _nombre.value = nombre
        _primerApellido.value = pApellido
        _segundoApellido.value = sApellido
    }

    fun onDataChange(estado: Pair<String,String>, sexo: String,fecha : LocalDate){
        _estado.value = estado
        _sexo.value = sexo
        _fecha.value = fecha
    }

    fun onShowChange(show : Boolean){
        _showCurp.value = show
    }

    fun generarCurp() : String{
        val vowels = listOf('a','e','i','o','u')

        var curp = _primerApellido.value?.substring(0,1)
        curp += _segundoApellido.value?.get(0)
        curp += _nombre.value?.get(0)
        curp += _fecha.value?.year.toString().substring(2,3)
        curp += _fecha.value?.month.toString()
        curp += _fecha.value?.dayOfMonth.toString()
        curp += _sexo.value
        curp += _estado.value?.first
        curp += _primerApellido.value?.substring(1)?.dropWhile { vowels.contains(it) }?.firstOrNull()
        curp += _segundoApellido.value?.substring(1)?.dropWhile { vowels.contains(it) }?.firstOrNull()
        curp += _nombre.value?.substring(1)?.dropWhile { vowels.contains(it) }?.firstOrNull()

        return curp!!
    }

}