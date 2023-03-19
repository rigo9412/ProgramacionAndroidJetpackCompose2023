package com.practica.curpmovil.form.ui



fun String.onlyLetters() = all { it.isLetter() }
fun String.onlySpecial() = all {  !it.isLetterOrDigit() }

fun getLetterforPosition(cad:String,pos:Int,cond : String):String{
    var char = cad[pos]
    var res:String
    if (cond.contains(char)){
        res = char.toString()
    }
    else{
        res = "X"
    }
    return res
}
fun getLetterforPosition(cad:String,pos:Int):String{
    var char = cad[pos]
    var res:String = char.toString()
    return res
}
fun clean(cad:String):String{
    var str : String = cad
    val re = "[^A-Za-z0-9 ]".toRegex()
    str = re.replace(str, "")
    return cad
}