// Elaborar una función que reciba un array de enteros y que invierta la primera
// posición por la segunda posición.

fun main(){
    var x = intArrayOf(1, 2, 3)
    reacomodar(x)
}

fun reacomodar(x: IntArray){
    var posicion1 = x[0]
    var posicion2 = x[1]
    var aguantame = 0

    x[0] = aguantame 
    x[1] = posicion1 
    x[0] = posicion2
    println(x.contentToString())

}
