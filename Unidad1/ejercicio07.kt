/* Elaborar una función que reciba un array de enteros y que invierta la primera
posición por la segunda posición.
 */

fun main() {
   print(invertir(15,22,45,66,77,88))
}

fun main() {
   print(invertir(15,22,45,66,77,88))
}
fun invertir(vararg array: Int): List<Int> {
    var list = ArrayList<Int>()
    var index = 2
    var i = 1
    while (index<array.size ) {
        if (i >=0 ) {
            list.add(array.get(i))
            i--
        } else {
            list.add(array.get(index))
            index++
        }
    }
    return list
}