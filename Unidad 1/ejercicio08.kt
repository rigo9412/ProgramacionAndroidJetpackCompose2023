//Cree una función que tome dos o más arrays y devuelva una array de sus
//diferencias. La array que se devuelve debe contener solo valores únicos (no
//duplicados).

fun main(){
    println(sacarDiferencia(arrayOf(3), arrayOf(1,2,3,4,0,0,"a","null"), arrayOf("2",1,0,"A",9,"NULL")).joinToString())

}


fun sacarDiferencia(vararg array: Array<Any>):Array<Any>
{
    var lista = mutableListOf<Any>()
    var diferentes = mutableListOf<Any>()

    for (i in array) diferentes.addAll(i)

    diferentes.groupBy{it.toString()}.forEach{
        if(it.value.count()==1) lista.add(it.key)
    }

    return lista.toTypedArray()
}