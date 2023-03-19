/*Elaborar una función que reciba un texto y cuente el número de letras que contiene.
 */

fun main() {
   print("Cantidad de letras: " + contarLetras("?Hol4 MunDO 4Andr01d K0TTTlin+ ++-"))
}


fun contarLetras(palabra: String): Int{
    var letras= 0
    for (char in palabra) {
        if(char.isLetter()) {
            letras++
        }
    }
    return letras
}