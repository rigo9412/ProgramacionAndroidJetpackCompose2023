//19100140 Ejercicio 8
//Cree una función que tome dos o más arrays y devuelva una array de sus diferencias. La array que se devuelve debe contener solo valores únicos (no duplicados).
//

fun main(){
   
    (ArrayDiferencia(arrayOf(3),arrayOf(3),arrayOf(2,3),arrayOf(1))).forEach{println(it)}
    
}

fun ArrayDiferencia(vararg arrays: Array<Any>): Array<Any> {
    val arregloComb = mutableListOf<Any>()
    val arregloRes = mutableListOf<Any>()
    
    for (item in arrays) arregloComb.addAll(item)
    
    arregloComb.groupBy{it.toString()}.forEach{
        if(it.value.count()==1) arregloRes.add(it.key)
    }
    
    return arregloRes.toTypedArray()
}