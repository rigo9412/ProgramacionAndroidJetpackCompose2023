import java.util.Arrays


fun main() {
    println(
        Arrays.toString( unionArreglos(
                arrayOf(3),
                arrayOf(1,2,3,4,0,0,"a","null"),
                arrayOf("2",1,0,"A",9,"NULL"))
        )
    )

}



fun unionArreglos(vararg arreglo: Array<Any?>): Array<Any> {

    val map :MutableMap<Any?, Any?> = mutableMapOf<Any?, Any?>()
    for (it in arreglo) {
        for (i in it) {
            map.put(i, 1)
        }
    }

    val lista = mutableListOf<Any>()
    for(key in map.keys) {
        if(key != null) {
            lista.add(key)
        }
    }

    return lista.toTypedArray()
}