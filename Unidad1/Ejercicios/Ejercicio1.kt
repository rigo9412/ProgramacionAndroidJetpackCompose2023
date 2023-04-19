fun main(){
 	reverseCadena("Ezequiel Cantu")
    reverseCadena(null)
}

fun reverseCadena(cadena: String?){
    if (cadena == null) return;
    else println(cadena.reversed()) 
    
    for (item in cadena.lastIndex downTo 0){
        print(cadena[item])
    }
    
    var index = cadena.length-1
    while (index >= 0){
         print(cadena[index])
         index--
    }
}
