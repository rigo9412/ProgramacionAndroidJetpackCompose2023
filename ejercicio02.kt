fun camelCase(palabra: String) :String {
    var camel = ""
    var cont = 0    
    var bandera: Boolean = false
    while(cont < palabra.length) {
        var letra = palabra.get(cont)
        
        if(letra != ' ' && letra != '-' && letra != '_' && letra != '.' && letra != ',' && letra != ';') {
           if(bandera) { //Es la primera
               camel += letra.toUpperCase()
           } else { //Es mayuscula
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
