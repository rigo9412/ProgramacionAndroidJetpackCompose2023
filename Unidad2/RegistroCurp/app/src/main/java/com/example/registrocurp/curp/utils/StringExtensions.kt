package com.example.registrocurp.curp.utils

fun String.onlyLetters() = all { it.isLetter() }
fun String.onlySpecial() = all {  !it.isLetterOrDigit() }
