//Elaborar una función que reciba un array de enteros y que invierta la primera
//posición por la segunda posición.

fun main() {
	println(invertirArray(65,13,33,69,14))
}

fun invertirArray(vararg array:Int): List<Int> {
    var list = ArrayList<Int>()
    var x = 1
    var index = 2
    while (index < array.size) 
    {
        if (x>=0) 
        {
            list.add(array.get(x))
            x--
        }
        else
        {
            list.add(array.get(index))
            index++
        }
    }
    return list
}