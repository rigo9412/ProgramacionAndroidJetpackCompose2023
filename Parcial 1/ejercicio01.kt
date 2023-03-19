//Elaborar una función que reciba una cadena de texto y la invierta.

fun main() {
    println(cadenaInvertida("Hello World!"))
    println(cadenaInvertida(null))
}

fun cadenaInvertida(cad: String?): String{
    if(cad == null) return "";
    var i: Int = cad.length - 1;
    var rev: String = "";
    while(--i >= 0){
        rev+=cad[i];
    }    
    return rev;
}
