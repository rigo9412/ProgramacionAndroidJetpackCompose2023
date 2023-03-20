import java.util.*
fun main(){
    println(Intercambio(arrayOf(1,2,3,4,5)).contentToString())
}

fun Intercambio(camb: Array<Int>):Array<Int>{
    camb[0] = camb[1].also{camb[1] = camb[0]}
    return  camb
}