//Elaborar una función que reciba una cadena de texto y la invierta.

fun main() {
    stringInvert("Hello, world!!!")
    cadenaInversa("cadena es un tipo de dato")
}

/*método visto en clase*/
fun stringInvert(value: String?) {
    if(value==null) return;
    
    for(item in value.lastIndex downTo 0){
        print(value[item])
    }
}


fun cadenaInversa(value:String?){
    if(value==null) return;
    print("\n"+value.reversed())