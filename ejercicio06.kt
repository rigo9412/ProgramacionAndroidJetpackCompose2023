//Elaborar una función que reciba 3 números enteros y que determine si el último
//dígito del número se repite entre alguno de los 3 números


fun main(){
    println(repetirDigito(1,4,4))
}

fun repetirDigito(a: Int, b: Int, c: Int): Boolean 
{    
    val digito = setOf(a % 10, b % 10, c % 10).size == 3
    return digito
}