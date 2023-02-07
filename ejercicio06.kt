//Ejercicio 6
//Elaborar una función que reciba 3 números enteros y que determine si el último
//dígito del número se repite entre alguno de los 3 números.
import java.util.ArrayDeque

fun main(){
    println(SeRepite(10,2,32))
}

fun SeRepite(x : Int, y : Int, z : Int) : Boolean{
    val nums = intArrayOf(x,y,z)
    val ultimos: IntArray = intArrayOf((x % 10), (y % 10), (z % 10))
    var stack = ArrayDeque<Int>()
    for (i in nums) {
        var temp = i
        temp = temp / 10
        while(temp != 0)
        {
            stack.push(temp % 10)
            temp = temp / 10
        }
    }
    return ultimos.intersect(stack).count() != 0
}