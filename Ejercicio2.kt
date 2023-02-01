fun main() {
    val cadenaTest = "Hola     MUNdo-Kotlin_Android-Jet_Pack"
    camelcaseTest(cadenaTest)
}
fun camelcaseTest(cadena: String) : String {
    
    val cadenaTestLow = cadena.lowercase().split(' ','-','_')
    var index = 1
    var cadenaTestUp = cadenaTestLow[0]
    while (index < cadenaTestLow.count()){
        cadenaTestUp = cadenaTestUp + cadenaTestLow[index].replaceFirstChar {it.uppercase()}
        index++
    }
    return(cadenaTestUp)
}