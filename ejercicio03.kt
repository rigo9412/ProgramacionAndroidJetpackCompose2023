/*Elaborar una función que reciba un texto y cuente el número de letras que contiene.
 */

fun main() {
   print(contarLetras("?Hol4 MunDO 4Andr01d K0TTTlin+ ++-"))
}

fun contarLetras(palabra: String): Map<Char, Int> {
    val letras = mutableMapOf<Char, Int>()
    for (char in palabra) {
        if(char.isLetter()){
            if (letras.containsKey(char)) {
                letras[char] = letras[char]!! + 1
            } else {letras[char] = 1}
        }
    }
    return letras
}