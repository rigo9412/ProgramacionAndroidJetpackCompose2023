// Elaborar una función que reciba un texto y cuente el número de letras que contiene.
// Test: ?Hol4 MunDO 4Andr01d K0TTTlin+ ++-

fun main (){
    println(contarLetras("Test: ?Hol4 MunDO 4Andr01d K0TTTlin+ ++-"))
}

fun contarLetras(miCadena: String): String?{
    var contador = 0    
    for(i in miCadena.indices){ //recorremos la cadena con ayuda del metodo "indices()"
        if(miCadena[i].isLetter()){ //validamos si el caracter es una letra
            contador = contador + 1 
        }
    }
    return contador.toString()
}