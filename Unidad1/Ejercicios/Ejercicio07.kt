//7. Elaborar una función que reciba un array de enteros y que invierta la primera
//posición por la segunda posición.

fun main(){
    var Array = intArrayOf(3,42,53,12,9)
    println(InvertirPosicion(Array))
}

fun InvertirPosicion(Array : IntArray){
    if (Array.isEmpty()){
        println("El arreglo esta vacio")
        return
    }
    else if(Array.size < 2){
        println("El arreglo es muy pequeño")
        return
    }
    val temp = Array[0]
    val temp2 = Array[1]
    Array[0] = temp2
    Array[1] = temp
    println(Array.joinToString(" "))
}
