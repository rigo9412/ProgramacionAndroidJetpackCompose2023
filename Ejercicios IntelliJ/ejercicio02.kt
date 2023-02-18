// Elaborar una función que convierta un texto de camelCase 
// Ejemplo : "Esto es una Cadena" -> "estoEsUnaCadena"

fun main() {
    println(stringConvertCamelCase("Hola     MUNdo-Kotlin_Android-Jet_Pack"))
}

fun stringConvertCamelCase(miCadena: String): String?{

    var cadenaCamelCase = "" 
    val palabras = miCadena.split("\\s|_|-".toRegex()).toTypedArray() //Guardamos las palabras en un arreglo
    //println(palabras.contentToString())

    val palabraSinMayuscula = palabras.first() //Obtenemos la primera palabra que no debe ser en mayusculas
    //println(palabraSinMayuscula)

    val palabrasConMayuscula = palabras.drop(1) //Guardamos en otro arreglo solo las palabras que tendran mayusculas
    //println(palabrasConMayuscula)

    //Agregamos primero la palabra que no tiene mayusculas en la variable auxiliar
    cadenaCamelCase = cadenaCamelCase + palabraSinMayuscula.toLowerCase() 

    for(palabra in palabrasConMayuscula){ 
        cadenaCamelCase = cadenaCamelCase + (palabra.toLowerCase()).capitalize() //Agregamos todas las palabras que van en mayusculas
    }
    return(cadenaCamelCase)
}