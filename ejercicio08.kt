//Cree una función que tome dos o más arrays y devuelva una array de sus diferencias.
//La array que se devuelve debe contener solo valores únicos (no duplicados).

//ARREGLAR
fun main() {
    val resultado = arrayUnico(
    arrayOf(3),
    arrayOf(1,2,3,4,0,0,"a","null"),
    arrayOf("2",1,0,"A",9,"NULL"))
   print(arrayUnico(arrayOf(resultado)))
}
fun arrayUnico(vararg array: Array<Any>) : String {
    var x = ArrayList<Any>()
    var y = ArrayList<Any>()
    for(item in array) x.addAll(item)
    x.groupBy{it.toString()
    }
    .forEach{ if(it.value.count() == 1) y.add(it.key)}
    return (y.joinToString())
}