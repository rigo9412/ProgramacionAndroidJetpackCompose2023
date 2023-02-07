fun main() {
   print(invertirPosiciones(80,26,67,46,17))
}

fun invertirPosiciones(vararg array: Int): String {
    var Arreglo = ArrayList<Int>()
    var index = 0
    var i = 1
    while (index < array.size ) {
        if (i >= 0 ) {
            Arreglo.add(array.get(i)) 
            i--
        }
        else {
            Arreglo.add(array.get(index)) 
            index++
        }
    }
    return Arreglo.toString().replace(Regex("""[{ }]"""), "")
}