fun main(){
    val text = "?Hol4 MunDO 4Andr01d K0TTTlin+ ++-"
    println("La cantidad de letras en el texto es: " + ContarLetras(text))
}

fun ContarLetras(texto : String): Int{
    var a : Int = 0
    var letras : CharArray = texto.toCharArray()
    for (i in letras) {
        if(i.isLetter())
        {
            a++
        }        
    }
    return a 
}