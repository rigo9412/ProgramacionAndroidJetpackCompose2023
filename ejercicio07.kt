//Elaborar una función que reciba un array de enteros y que invierta la primera
//posición por la segunda posición

fun main(){
    val arreglo = intArrayOf(1, 2, 3, 4, 5)
    invertirPos(arreglo)
    println(arreglo.joinToString(" "))
}

fun invertirPos(arreglo: IntArray) {
    if (arreglo.size < 2) return
    val temp = arreglo[0]
    arreglo[0] = arreglo[1]
    arreglo[1] = temp
}