// Elaborar una función que reciba un texto y cuente el número de letras que contiene
fun contarLetras(str: String): Int {
    return str.filter { it.isLetter() }.length
}

fun main() {
    println(contarLetras("Hol4 MunDO 4Andr01d K0TTTlin+ ++-"))
}