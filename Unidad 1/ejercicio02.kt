// Elaborar una función que convierta un texto de camelCase 
// Ejemplo : "Esto es una Cadena" -> "estoEsUnaCadena"

fun main(){
    convertir("Esto es una Cadena")
}

fun convertir(value: String){
    if(value == null) return;
    
    var str = ""
    var oracion = value.split(" ")
    var x =oracion.count()
    var i = 0
    
    println("Numero de palabras: $x")
    
    while (i < x)
    {
        if (i==0){
            str=oracion[i].replaceFirstChar { it.lowercase() }
        }
		println(str)
        str= str + oracion[i].replaceFirstChar { it.uppercase() }
        i++
    }
		println(str)
}