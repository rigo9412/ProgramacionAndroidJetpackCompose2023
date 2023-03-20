fun main() {
    val concat = combinacion(arrayOf(3),
    arrayOf(1,2,3,4,0,0,"a","null"),
    arrayOf("2",1,0,"A",9,"NULL")).contentToString()
     println(concat)
 }
 
 fun combinacion(vararg data: Array<Any>): Array<Any>{
     val list = mutableListOf<Any>()
     for(item in data) list.addAll(item)
     return list.toSet().toTypedArray()
 }