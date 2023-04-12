// Elaborar una función que reciba un texto y cuente el número de letras que contiene.
fun main() {
    print(contarletras("?Hol4 MunDO 4Andr01d K0TTTlin+ ++-"))
}

fun contarletras(frase : String) : Int {
    var cont = 0
    for (i in 0..frase.lastIndex) {
        if (frase[i].isLetter()) {
            cont++
        }
    }
    return cont
}