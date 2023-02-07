/* Cree una función que tome dos o más arrays y devuelva una array de sus
diferencias. La array que se devuelve debe contener solo valores únicos (no
duplicados).
 */

fun main() {
   val items = listOf(1,2,3,4)
   val items1 = listOf(1,1,3,7)
   diferencias(items,items1)
}
fun <T> diferencias(vararg array: List<T>) {
    var list = ArrayList<T>()
    var u = 0
    var index = 0
    while(index < array.size){
        while(u < array.get(index).size){
            list.add(array.get(index).get(u))
        u++
    }
        index++
        u=0
    }
    val x = list.distinct()
    print (x)
}