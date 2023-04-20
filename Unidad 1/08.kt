//// EJERCICIO 8
//// Cree una función que tome dos o más arrays y devuelva una array de susdiferencias.
//// La array que se devuelve debe contener solo valores únicos (noduplicados).
import java.util.Arrays

fun combinarArreglos(vararg arrays: Array<Any?>): Array<Any> {

    //Primero convertimos los arreglos a arreglos de string
    val listaConvertidos:mutableListOf<Array<String>>()
    for (it in arrays) {
        val arregloAuxiliar: Array<String> = it.toList().map { i -> i.toString() }.toTypedArray()
        listaConvertidos.add(arregloAuxiliar)
    }

    //Despues interceptamos todos los arreglos convertidos
    var interceptados = listaConvertidos[0]
    for(i in 0 until listaConvertidos.size - 1){
        val temp = listaConvertidos[i].toTypedArray().intersect(listaConvertidos[i + 1])
        interceptados = interceptados.toTypedArray().intersect(temp)

        
    }

    //Al final agregamos todos los elementos de cada arreglo, siempre y cuando no este en mi intercepcion
    
    // val list = mutableListOf<Any>()
    // for(key in map.keys) {
    //     if(key != null) {
    //         list.add(key)  
        
        
    //     }
    // }

    // return list.toTypedArray()
    return arrayOf(1,2,3,4,5,"3","a")
}

fun main() {

//   val firstArray = arrayOf(1,2,3,4,5,"3","a")
//     val secondArray = arrayOf(2,5,6,7,"3",null)

//     val dest: Array<String> = firstArray.toList().map { i -> i.toString() }.toTypedArray()
//     val dest2: Array<String> = secondArray.toList()
//         .map { i -> i.toString() }
//         .toTypedArray()


//     println(dest[1]+2)
//     println(Arrays.toString(dest2))
    // println(Arrays.toString(dest.intersect(dest2.toList()).toTypedArray()))

    //Los elementos que solo estan una vez entre todos los arreglos
    println(Arrays.toString(combinarArreglos(arrayOf(1,2,3,4,0,0,"a","null"), arrayOf("2",1,0,"A",9,"NULL"))))
}
