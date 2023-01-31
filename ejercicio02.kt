//19100140 Ejercicio 2
////Elaborar una función que convierta un texto de camelCase 
//Ejemplo : "Esto es una Cadena" -> "estoEsUnaCadena"
//

fun main()
{
    print(ToCamelCase("STR Variable 1"))
}


fun ToCamelCase(valor : String) : String
{
    var cadmat = valor.split(" ")
    var priCad = cadmat[0].lowercase()
    var camelCase = priCad
    for (i in 1..(cadmat.lastIndex) )
    {
        camelCase=camelCase + cadmat[i].capitalize()
    }
    return camelCase
}