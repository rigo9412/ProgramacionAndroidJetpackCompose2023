fun main() {
    val cadenaTest = "Hola     MUNdo-Kotlin_Android-Jet_Pack"
    print(camelcaseTest(cadenaTest))
    
}
fun camelcaseTest(cadena: String) : String {
    
    val cadenaTestLow = cadena.lowercase().split(' ','-','_')
    var i = 1
    var cadenaTestUp = cadenaTestLow[0]
    while (i < cadenaTestLow.count()){
        cadenaTestUp = cadenaTestUp + cadenaTestLow[i].replaceFirstChar {it.uppercase()}
        i++
    }
    return(cadenaTestUp)
}
