//Elaborar una función que reciba un texto y cuente el número de letras que contiene.

fun main(){
    println(CountLetters("-112Hola ! _ 034 - Soy -Jordan"))
}


fun CountLetters(value:String):Int{
    return Regex("[^A-Za-z]").replace(value,"").length
}
