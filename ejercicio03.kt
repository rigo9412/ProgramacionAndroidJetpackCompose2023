//Elaborar una función que reciba un texto y cuente el número de letras que contiene.

fun main()
{
    print(contarLetras("?Hol4 MunDO 4Andr01d K0TTTlin+ ++-"))
}


fun contarLetras(string : String) : Int
{
    var count = 0
    for (x in 0..string.lastIndex) 
    {
        if (string[x].isLetter()) 
        {
            count++
        }
    }
    return count
}