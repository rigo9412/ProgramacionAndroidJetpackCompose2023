fun diferencias(vararg arrays: Array<Int>): Array<Int> {
    val merged = arrays.reduce { acc, array -> acc + array }
    return merged.distinct().toTypedArray()
}

fun main(args: Array<String>) {
    println(diferencias(arrayOf(1, 2, 3), arrayOf(2, 5, 2, 1, 4, 7, 1)).contentToString())
    println(diferencias(arrayOf(1, 2, 3), arrayOf(2, 5, 2, 1, 4, 9), arrayOf(2, 1, 2)).contentToString())
}