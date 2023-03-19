//Elaborar una función que convierta un texto de camelCase 
//Ejemplo : "Esto es una Cadena" -> "estoEsUnaCadena"

fun main(){
    println(camelCase("Hola     MUNdo-Kotlin_Android-Jet_Pack"))
}

fun camelCase(string: String?) {
    if(string == null) 
    return
    var i = 0
    var x = ""
    var list = string.lowercase().split('_','-',' ')
    while (i < list.count())
    {
        x = x + list[i].replaceFirstChar { 
            it.uppercase() 
        }
        i++
    }
    println(x.replaceFirstChar 
            { 
                it.lowercase() 
            }
           )
}