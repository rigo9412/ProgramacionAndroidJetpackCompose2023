//Elaborar una función que reciba una cadena de texto y la invierta.

fun main() {
    // EJERCICIO 1
    invertidostr("Holaaaa")



    // EJERCICIO2
    var str = "Rene Alexis salinas a"
    print(str.lowercase().CamelStr())
}

// EJERCICIO 1
fun invertidostr(value: String? = "default"){
    if(value == null) return;
    for(item in value.lastIndex downTo 0){
        print(value[item])
    }
}


// EJERCICIO 2
// Elaborar una función que convierta un texto de camelCase 
// Ejemplo : "Esto es una Cadena" -> "estoEsUnaCadena"

val cadena = "\\s+[a-zA-Z]".toRegex()

fun String.CamelStr(): String {
    return cadena.replace(this) { 
        it.value.replace(" ","")
            .uppercase() 
    }
    
}

