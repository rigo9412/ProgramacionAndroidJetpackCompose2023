// Elaborar una función que reciba un texto y cuente el número de letras que contiene.

fun main() {
    val oracion = "Test: ?Hol4 MunDO 4Andr01d K0TTTlin+ ++-"
    // println(palabra.length)

    var contador = 0
    for (i in oracion.length - 1 downTo 0) {
        // print(s[i])
        if(oracion[i] >= 'a' && oracion[i] <= 'z' || oracion[i] >= 'A' && oracion[i] <= 'Z'){
        contador = contador + 1}
    }
    print(contador)
}
    
