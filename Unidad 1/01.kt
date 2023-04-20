////EJERCICIO 1
////Elaborar una función que reciba una cadena de texto y la invierta.


fun invertString(cad: String): String {
    var cont = cad.length - 1
    var invertido = ""
    
    while(cont >= 0) {
        invertido += cad.get(cont)
        cont -= 1
    }    	
        
    return invertido
}


fun main() {
    var inv = invertString("HOLA ESTA ES UNA PRUEBA")
    println(inv)
}