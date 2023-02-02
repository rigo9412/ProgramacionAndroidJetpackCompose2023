fun main(){
    print(convertir("Hola     MUNdo-Kotlin_Android-Jet_Pack"))
}

fun convertir(texto: String): String{   
    val letras: CharArray = texto.toCharArray()
    var camel : String = ""
    var n = texto.length - 1
    var i = 0
    while (i<=n)
    {
        if(letras[i].isUpperCase() && i==0)
        {
            camel = camel + letras[i].lowercase()
            i++
            continue
        }
        if(letras[i].isUpperCase())
        {
            camel = camel + letras[i]
            i++
            while(letras[i].isUpperCase())
            {
                camel = camel + letras[i].lowercase()
                i++
            }
            continue
        }
        if(letras[i].isLowerCase())
        {
            camel = camel + letras[i]
            i++
            continue
        }
        if(letras[i] == '-' || letras[i] == '_' || letras[i] == ' ')
        {
            i++
            continue
        }         
    }
    return camel
}