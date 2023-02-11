import java.util.*

fun main() {
    println(Arrays.toString(invertir(arrayOf(1,4,6,8,2,4,5,9))))
}

fun invertir(arreglo :Array<Int>) :Array<Int> {
    var respaldo = arreglo[1]
    arreglo[1] = arreglo[0]
    arreglo[0] = respaldo

    return arreglo
}
