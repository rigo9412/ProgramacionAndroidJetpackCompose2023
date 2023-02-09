//Elaborar una función que reciba un array de enteros y que invierta la primera
//posición por la segunda posición.

import java.text.Normalizer
fun main() {
    var enteros:IntArray = intArrayOf(10, 20, 30, 40, 50)
    invertirArreglos(enteros)
}

fun invertirArreglos(arreglo:IntArray):IntArray{

    var x=0
    var y=arreglo[0]
    var z=arreglo[1]
            arreglo[x]=z
            x++
            arreglo[x]=y
    for(i in arreglo){
        println(i)
    }
    return arreglo
}