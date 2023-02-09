fun main() {
    print(invertedArray().contentToString())
}

fun invertedArray(): IntArray {
    var arr = intArrayOf(6,5,4)
    var aux1 = arr[0]
    var aux2 = arr[1]

    arr = arr.drop(1).toIntArray()
    arr = arr.drop(1).toIntArray()

    val result = arr.toMutableList()
    result.add(0, aux2)
    result.add(1, aux1)
    return result.toIntArray();
}