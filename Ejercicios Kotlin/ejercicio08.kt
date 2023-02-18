//Cree una función que tome dos o más arrays y devuelva una array de sus diferencias.
//La array que se devuelve debe contener solo valores únicos (noduplicados).
fun main(args: Array<String>) {
    print(eliminarDuplicados(arrayOf(3),
        arrayOf(1,2,3,4,0,0,"a","null"),
        arrayOf("2",1,0,"A",9,"NULL")))
}

//[[3],[1,2,3,4,5,0,0],[2,1,0,A,9]]

fun eliminarDuplicados(vararg arrays: Array<Any>): String {
    val listaArrays = arrays.toList()
    val listaCombinada = ArrayList<Any>()

    //Crear la lista combinada de todos los arreglos
    for(i in 0 until listaArrays.size){
        listaCombinada.addAll(listaArrays[i])
    }


    return(listaCombinada.distinct().toString())

}