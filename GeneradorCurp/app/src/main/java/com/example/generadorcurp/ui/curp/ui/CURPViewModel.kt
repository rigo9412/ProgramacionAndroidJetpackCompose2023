package com.example.generadorcurp.ui.curp.ui

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

    private val _estado = MutableLiveData<String>()
    val estado: LiveData<String> = _estado

    private val _sexo = MutableLiveData<String>()
    val sexo: LiveData<String> = _sexo

    private val _fecha = MutableLiveData<LocalDate>()
    val fecha: LiveData<LocalDate> = _fecha

    fun onCurpChange(nombre: String, pApellido: String, sApellido: String, estado: String, sexo: String,fecha : LocalDate) {
        _nombre.value = nombre
        _primerApellido.value = pApellido
        _segundoApellido.value = sApellido
        _estado.value = estado
        _sexo.value = sexo
        _fecha.value = fecha
    }

    fun generarCurp(){
        val vowels = listOf('a','e','i','o','u')

        var curp = _primerApellido.value?.substring(0,1)
        curp += _segundoApellido.value?.get(0)
        curp += _nombre.value?.get(0)
        curp += _fecha.value?.year.toString().substring(2,3)
        curp += _fecha.value?.month.toString()
        curp += _fecha.value?.dayOfMonth.toString()
        curp += _sexo.value
        curp += _estado.value
        curp += _primerApellido.value?.substring(1)?.dropWhile { vowels.contains(it) }?.firstOrNull()
        curp += _segundoApellido.value?.substring(1)?.dropWhile { vowels.contains(it) }?.firstOrNull()
        curp += _nombre.value?.substring(1)?.dropWhile { vowels.contains(it) }?.firstOrNull()


    }


}