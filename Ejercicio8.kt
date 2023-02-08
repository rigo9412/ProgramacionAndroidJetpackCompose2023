fun main(){  
    print(combinarArrays(arrayOf(1,2,3,4,0,0,"a","null"), arrayOf("2",1,0,"A",9,"NULL")).joinToString())   
}
fun combinarArrays(vararg arreglos: Array<Any>): Array<Any> {
    val Com = mutableListOf<Any>()
    val Dif = mutableListOf<Any>()
    for (item in arreglos) Com.addAll(item)
    Com.groupBy{it.toString()}.forEach{ if(it.value.count()==1) Dif.add(it.key) } 
    return Dif.toTypedArray()
}