/*Elaborar una función que convierta un texto de camelCase 
 * Ejemplo : "Esto es una Cadena" -> "estoEsUnaCadena"
 */

fun main() {
    camelCase("Esto es una Cadena")
}

fun camelCase(cadena: String?) {
    if(cadena == null) return
    var lista = cadena.split(" ")
    var index = 0
    var up = ""
    while (index < lista.count()){
        up = up + lista[index].replaceFirstChar { it.uppercase() }
        index++
    }
    println(up.replaceFirstChar { it.lowercase() })
}