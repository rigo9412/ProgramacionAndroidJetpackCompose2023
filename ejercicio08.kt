fun main (){
    println("3,4,a,null,A,9,NULL")
    println(result)
}
val result = combineArrays(arrayOf(1,2,3,4,0,0,"a","null"), arrayOf("2",1,0,"A",9,"NULL")).contentToString()

fun combineArrays(vararg data:Array<Any>):Array<Any>{
    val Arrays = mutableListOf<Any>()
    for(item in data) Arrays.addAll(item)
    return Arrays.toSet().toTypedArray()
}