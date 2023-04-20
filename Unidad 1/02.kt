////EJERCICIO 2
////Elaborar una función que convierta un texto de camelCase

fun camelCase(palabra: String) :String {        
    var camel:String = ""
    var cont = 1    
    var bandera: Boolean = false
    var tamanio = palabra.length
    
    camel += palabra[0].toLowerCase()
    
    while(cont < tamanio) {
        var letra = palabra[cont]
        
        if(letra != ' ' && letra != '-' && letra != '_' && letra != '.'&& letra != ',' && letra != ';') {
           if(bandera) { //Es la primera
               camel += letra.toUpperCase()
           } else {
               camel += letra.toLowerCase()
           }
           
           bandera = false
        } else {
            bandera = true
        }
        
        cont += 1
    }    
    
    return camel
}

fun main() {
    println(camelCase("Hola       MUNdo-Kotlin_Androis-Jet_Pack"))
}
