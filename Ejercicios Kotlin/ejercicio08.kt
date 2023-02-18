fun diferencias(vararg arrays: Array<Any>): String {
    val aplanado = arrays.flatten().map { it.toString() }
    return aplanado.toSet().filter { elem ->  aplanado.count { it == elem } == 1 }.joinToString(",")
}

fun main() {
    val x = diferencias(arrayOf(3),
        arrayOf(1,2,3,4,0,0,"a","null"),
        arrayOf("2",1,0,"A",9,"NULL"))
    println(x)
}