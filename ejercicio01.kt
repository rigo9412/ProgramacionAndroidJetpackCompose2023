//Elaborar una función que reciba una cadena de texto y la invierta.

fun main() {
    Invertir("Hola mundo!!!")
}

fun Invertir(value: String?) {
    if(value == null) return
    
    /* Con for

for (item in value.lastIndex downTo 0) {
        print(value[item])
    }*/
// Con while
    var index = value.length-1
    while (index >= 0){
        print(value[index])
        index--
    }
}