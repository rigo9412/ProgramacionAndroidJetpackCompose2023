//Elaborar una función que reciba un array de enteros y que invierta la primera
//posición por la segunda posición
import java.util.*

fun main(){
    println(InvertirPos(intArrayOf(10,2,32)))
}

fun InvertirPos(arreglo : IntArray){
    arreglo[1] = arreglo[0].also {arreglo[0] = arreglo[1]}
}