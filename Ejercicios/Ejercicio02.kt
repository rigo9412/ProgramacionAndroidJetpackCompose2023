// Elaborar una función que convierta un texto de camelCase 
// Ejemplo : "Esto es una Cadena" -> "estoEsUnaCadena"
fun main(){
    print(camelCase("Hola     MUNdo-Kotlin_Android-Jet_Pack"))
}

fun camelCase(str : String) : String{
    var palabras = str.split(" ","_","-",";")
    var palabraminuns = palabras[0].lowercase()
    var stringEnCamelCase = palabraminuns
    for (i in 1..(palabras.lastIndex))
    {
        stringEnCamelCase = stringEnCamelCase + palabras[i].lowercase().capitalize()
    }
    return stringEnCamelCase
}
