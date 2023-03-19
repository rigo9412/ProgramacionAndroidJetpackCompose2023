/*Elaborar una función que convierta un texto de camelCase 
 * Ejemplo : "Esto es una Cadena" -> "estoEsUnaCadena"
 */

fun main() {
    camelCase("Hola     MUNdo-Kotlin_Android-Jet_Pack")
}

fun camelCase(cadena: String?) {
    if(cadena == null) return
    var lista = cadena.lowercase().split(' ','_','-')
    var index = 1
    var up = lista[0]
    while (index < lista.count()){
        up = up + lista[index].replaceFirstChar { it.uppercase() }
        index++
    }
    println(up.replaceFirstChar { it.lowercase() })
}