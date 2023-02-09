fun main(){
    var arreglo : IntArray = intArrayOf(1,2,3,4,5)
    
    for(c in InvertirPosicion(arreglo))
    {
        println(c)
    }
}

fun InvertirPosicion(array : IntArray) : IntArray
{
    var array1 = emptyArray<Int>()
    
    array1 += array.component2()
    array1 += array.component1()

    var array2 = array.drop(2)
    for(c in array2)
    {
        array1 += c
    }
    return array1.toIntArray()
}