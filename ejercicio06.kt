//Elaborar una función que reciba 3 números enteros y que determine si el último
//dígito del número se repite entre alguno de los 3 números


fun main(){
    println(repetirDigito(1,4,4))
}

fun repetirDigito(a: Int, b: Int, c: Int): Boolean {
    val x = (a % 10).toString()
    val y = (b % 10).toString()
    val z = (c % 10).toString()
    var num1 = (a / 10).toString()
    var num2 = (b / 10).toString()
    var num3 = (c / 10).toString()
    return num1.contains(x) || num1.contains(y) || num1.contains(z) ||
           num2.contains(x) || num2.contains(y) || num2.contains(z) ||
           num3.contains(x) || num3.contains(y) || num3.contains(z)
}