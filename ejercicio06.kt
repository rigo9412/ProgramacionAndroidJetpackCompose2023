//19100140 Ejercicio 6
//Elaborar una función que reciba 3 números enteros y que determine si el último dígito del número se repite entre alguno de los 3 números.
//

fun main(){
    print(determinarUltimoDigito(33,37,44))
}

fun determinarUltimoDigito(a: Int, b: Int, c: Int): Boolean {
    val digitoA = a % 10
    val digitoB = b % 10
    val digitoC = c % 10
    return (digitoA == digitoB || digitoA == digitoC || digitoB == digitoC)
} 
