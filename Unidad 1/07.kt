////EJERCICIO 7
////Elaborar una función que reciba un array de enteros y que invierta la primera posición por la segunda posición.
import java.util.Arrays

fun invertirPosicion12(array :Array<String>) :Array<String> {
    var p2 = array[1]
    array[1] = array[0]
    array[0] = p2
    
    return array
}

fun main() {
    println(Arrays.toString(invertirPosicion12(arrayOf("1", "2", "3", "4", "5", "6", "7"))))
}
