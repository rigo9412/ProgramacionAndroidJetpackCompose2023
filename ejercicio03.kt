//3.- Elaborar una funcin que reciba un texto y cuente el numero de letras que contiene.

fun main() {
    println(ContarLetras("Esto es una Cadena"))
    println(ContarLetras("?Hol4 MunDO 4Andr01d K0TTTlin+ ++-"))
}

fun ContarLetras(cadena : String?) : Int{
    if(cadena == null) {
        return 0
    }
    var cont = 0
    var letras = 0
    while(cont < cadena.length){
        if(cadena[cont].isLetter()){
            letras += 1
        }
        cont += 1
    }
    return letras
}