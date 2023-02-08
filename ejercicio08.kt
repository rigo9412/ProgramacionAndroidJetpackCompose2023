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
    var index = 0
    var u = 0
    while(index < array.size){
        while(u < array.get(index).size){
            list.add(array.get(index).get(u))
        u++
    }
        index++
        u=0
    }
    val diferencia = list.distinct()
    print (diferencia)
}

fun main() {
   print(diferencias(arrayOf(3),arrayOf(1,2,3,4,0,0,"a","null"), arrayOf("2",1,0,"A",9,"NULL")).joinToString())
   
}
fun diferencias(vararg array: Array<Any>) : Array<Any> {
    val resultArray = mutableListOf<Any>()
    val contains = mutableListOf<Any>()
    for(item in array) contains.addAll(item)
    
    contains.groupBy{it.toString()}.forEach{
        if(it.value.count() == 1) resultArray.add(it.key)
    }
    return resultArray.toTypedArray()
}