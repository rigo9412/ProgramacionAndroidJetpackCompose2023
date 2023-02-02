// Elaborar una función que convierta un texto de camelCase 
// Ejemplo : "Esto es una Cadena" -> "estoEsUnaCadena"

fun main() {
    println(camelCase
("Hola como estas"))
}

fun camelCase(string: String, delimiter: String = " ", separator: String = " "): String {
    return string.split(delimiter).joinToString(separator = separator) {
        it.lowercase().replaceFirstChar { char -> char.titlecase() }
    }
}



