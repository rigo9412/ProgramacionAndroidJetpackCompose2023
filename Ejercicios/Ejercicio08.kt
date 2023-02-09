//8. Cree una función que tome dos o más arrays y devuelva una array de sus
//diferencias. La array que se devuelve debe contener solo valores únicos (no duplicados).
fun main(){
    val miarreglo = arrayOf<Any>(1,2,3,4,0,0,"a","null")
    val miarreglo2 = arrayOf<Any>("2",1,0,"A",9,"NULL")
    println(arrayValoresUnicos(miarreglo,miarreglo2))
}

fun arrayValoresUnicos(vararg arrays: Array<Any>) : String {
    var arrayList = ArrayList<Any>()
    var resultArrayList = ArrayList<Any>()
    for(item in arrays) {
        arrayList.addAll(item)
    }
    arrayList.groupBy{it.toString()}.forEach{
        if(it.value.count() == 1) resultArrayList.add(it.key)
    }
    return (resultArrayList.toString())
}