fun invierteCadena(cad: String?): String{
    if(cad == null) return "";
  	// return cad.reversed();
    var i: Int = cad.length - 1;
    var rev: String = "";
    while(--i >= 0){
        rev+=cad[i];
    }    
    return rev;
}


fun main() {
    println(invierteCadena("Hola !!"))
    println(invierteCadena(null))
}