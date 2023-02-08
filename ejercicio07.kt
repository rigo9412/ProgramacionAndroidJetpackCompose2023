fun invertirPosiciones2(array: Array<Int>): Array<Int> {
    return arrayOf(array[1], array[0], array[2])
}

fun main(args: Array<String>) {
    println(invertirPosiciones(arrayOf(1, 2, 3)).contentToString())
    println(invertirPosiciones2(arrayOf(4, 5, 6)).contentToString())
}