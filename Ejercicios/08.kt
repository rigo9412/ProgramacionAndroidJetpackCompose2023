//// EJERCICIO 8
//// Cree una función que tome dos o más arrays y devuelva una array de susdiferencias.
//// La array que se devuelve debe contener solo valores únicos (noduplicados).
import java.util.Arrays

fun combinarArreglos(vararg arrays: Array<Any?>): Array<Any> {
    val map :MutableMap<Any?, Any?> = mutableMapOf<Any?, Any?>()
    for (it in arrays) {
        for (i in it) {
            map.put(i, 1)
        }
    }

    val list = mutableListOf<Any>()
    for(key in map.keys) {
        if(key != null) {
            list.add(key)  
        }
    }

    return list.toTypedArray()
}

fun main() {
    println(
            Arrays.toString(
                    combinarArreglos(
                            arrayOf(1, 2, 3),
                            arrayOf(1, 2, null),
                            arrayOf("a", "b", "c"),
                            arrayOf("f", "g", "h"),
                            arrayOf("f", "g", "null"),
                            arrayOf("f", "g", "h"),
                            arrayOf("a", "b", "c")
                    )
            )
    )
}
