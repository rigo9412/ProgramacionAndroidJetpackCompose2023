fun diferencias(vararg arrays: Array<Any>): String {
    return arrays.map { it.map { any -> any.toString()  }  }.reduce{ acc, arr -> acc + arr}.distinct().joinToString(",")
}

fun main() {
    val x = diferencias(arrayOf(3),
        arrayOf(1,2,3,4,0,0,"a","null"),
        arrayOf("2",1,0,"A",9,"NULL"))
    println(x)
}