package com.example.generadorcurp.form.domain

import java.text.Normalizer

fun normalizarNombre(nombre: String) : String{
    var nombreNormal = nombre
    nombreNormal.replace("Ñ","X")
    nombreNormal = Normalizer.normalize(nombre, Normalizer.Form.NFD)

    return nombreNormal
}

fun getInternalVowel(nombre: String) : Char{
    val vowels = listOf('A','E','I','O','U')
    var counter = 1
    while(counter < nombre.length){
        if(vowels.contains(nombre[counter])){
            break
        }
        counter++
    }
    return nombre[counter]
}

fun getInternalConsonant(nombre : String) : Char{
    val vowels = listOf('A','E','I','O','U')
    var counter = 1
    while(counter < nombre.length){
        if(vowels.contains(nombre[counter])){
            counter++
            continue
        }
        break
    }
    return nombre[counter]
}

fun calcularUltimoDigitoCURP(curp: String): Int {
    val vowels = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
    var suma = 0
    var counter = 18
    for (i in 0 until 17) {
        val c = curp[i]
        val valor = vowels.indexOf(c)
        suma += valor * counter
        counter += -1
    }

    val residuo = suma % 10
    return if (residuo == 0) 0 else 10 - residuo
}