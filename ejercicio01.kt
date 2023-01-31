//Ejercicio 1 - 19100140
////Elaborar una función que reciba una cadena de texto y la invierta.
fun main() {
    
    println(Invertir("Hola"))
}

fun InvertirTexto(cadenas : String? = "defult") : String {
    var strCadenaRecibida : String = cadenas
    var strInv = ""
    for (i in strCadenaRecibida.lastIndex downTo 0){
        strInv = strInv + strCadenaRecibida[i]
    }
    
	return (strCadenaRecibida.reversed())
    
}