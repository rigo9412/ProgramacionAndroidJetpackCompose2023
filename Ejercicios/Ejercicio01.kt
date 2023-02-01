//Elaborar una función que reciba una cadena de texto y la invierta.
// Opcion 1 - For
fun main(){
    StringInvertir("Hola como estas?")
}
fun StringInvertir(value: String? = "default"){
    if (value  == null) return;
    for (item in value.lastIndex downTo 0){
        print(value[item])
    }
}


// Opcion 2 - While
fun main(){
    cadenainvertida("Hola")
}
fun cadenainvertida(str: String? = "default") {
    if (str == null) return;
    var result: String = ""
    var lastIndex = str.lastIndex
    while (lastIndex >= 0) {
        result += str[lastIndex]
        lastIndex--
    }
    println("La cadena invertida es: " + result)
}