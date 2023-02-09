//Cree una función que tome dos o más arrays y devuelva una array de sus diferencias.
//La array que se devuelve debe contener solo valores únicos (noduplicados).
fun main(args: Array<String>) {
    print(EliminarDuplicados(arrayOf(3),
        arrayOf(1,2,3,4,0,0,"a","null"),
        arrayOf("2",1,0,"A",9,"NULL")))
}

fun EliminarDuplicados(vararg arrays: Array<Any>): String {
    var listaArrays = arrays.toList()
    var listaAuxiliar = listaArrays.get(0).toList()
    var listaCombinada = ArrayList<Any>()

    for(i in 0 until listaArrays.size){
        if (i == 0){
            var listaCombinada = listaArrays.get(0).toList()
        }else{
            listaCombinada.addAll(listaArrays.get(i))
        }
    }
    for(i in 0 until listaArrays.size){
        if(i == 0){
            continue
        }else{
            listaCombinada = listaArrays.get(i).toList().minus(listaCombinada.toList()) as ArrayList<Any>
        }
    }
    return(listaCombinada.toString())
}