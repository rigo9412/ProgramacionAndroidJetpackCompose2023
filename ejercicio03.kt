// Elaborar una función que reciba un texto y cuente el número de letras que contiene
fun contarLetras(cadena: String): Int {
    return cadena.replace("\\s|,|[.]|;|[0-9]".toRegex(), "").length
}

fun main() {
    println(contarLetras("ABC DEF GHI 9"))
}