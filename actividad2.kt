


fun main()
{
    print(ConvCamel("Hola  /   MUNdo-Kotlin_Android-Jet*Pack"))
}


fun ConvCamel(texto : String) : String
{
    var separaciones = texto.split(" ","-","_","=","/","*","@","!")
    var cadenaInicial = separaciones[0].lowercase()
    var camelCase = cadenaInicial
    for (i in 1..(separaciones.lastIndex) )
    {
        camelCase=camelCase + separaciones[i].lowercase().capitalize()
    }
    return camelCase
}
