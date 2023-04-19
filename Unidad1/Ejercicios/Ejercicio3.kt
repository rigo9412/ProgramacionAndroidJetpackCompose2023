fun main()
{
    print("Cantidad de letras en el texto: " + countLetters("Esto es una cadena"))
}

fun countLetters(letra : String) : Int
{
    var cantLet = 0
    for (i in 0..letra.lastIndex){
        if(letra[i].isLetter()) cantLet++
    }
    return cantLet
}
