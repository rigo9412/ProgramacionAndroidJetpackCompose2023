//Elaborar una función que reciba un array de enteros y que invierta la primera
//posición por la segunda posición.

import java.util.*
fun main(){
    println(InvertArray(arrayOf(1,2,3,4,5)).contentToString())
}

fun InvertArray(numbs: Array<Int>):Array<Int>{
    val numbsArray = numbs
    Collections.swap(numbsArray.asList(),0,1)
    return  numbsArray
}