/* Cree una función que tome dos o más arrays y devuelva una array de sus
diferencias. La array que se devuelve debe contener solo valores únicos (no
duplicados).
 */

fun main() {
    val result = diferencias(
    arrayOf(3),
    arrayOf(1,2,3,4,0,0,"a","null"),
    arrayOf("2",1,0,"A",9,"NULL"))
   print(diferencias(arrayOf(result)))
}
fun diferencias(vararg array: Array<Any>) : String {
    var list = ArrayList<Any>()
    var list1 = ArrayList<Any>()
    for(item in array) list.addAll(item)
    list.groupBy{it.toString()}.forEach{
        if(it.value.count() == 1) list1.add(it.key)
    }
    return (list1.joinToString())
}