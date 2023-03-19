//19100140 Ejercicio 7
//Elaborar una función que reciba un array de enteros y que invierta la primera posición por la segunda posición.
//

fun main(){
    val arreglo = intArrayOf(3,5,7,8,4)
    invertirExtremos(arreglo)
    arreglo.forEach{println(it)}
}

fun invertirExtremos(array: IntArray) {
    if (array.size < 2) {
    return 
    }
    val aux = array[0]
    array[0] = array[1]
    array [1] = aux
}