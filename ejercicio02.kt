fun camelCase(cadena: String): String {
    return cadena.split("\\s|_|-".toRegex()).joinToString(separator = "") { p -> p.replaceFirstChar { it.uppercase() } }.replaceFirstChar { it.lowercase() }
}

fun main(){
    println(camelCase("hola mundo"))
    println(camelCase("hola mundo-o"))
    println(camelCase("variable"))
    println(camelCase("prueba_guiones"))
    println(camelCase("prueba_guiones espacio"))
    println(camelCase("Esto es una Cadena"))
}