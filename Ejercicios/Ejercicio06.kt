//6. Elaborar una función que reciba 3 números enteros y que determine si el último
//dígito del número se repite entre alguno de los 3 números.

/*
Version 1 println(isInLastDigit(1,4,4)) = false
Versión 2 - En esta versión hay que hacer la modificación para que el resultado sea verdadero.
Hay que comparar el ultimo con todos los números restantes entre si, y así con los otros dos numeros ingresados:
println(isInLastDigit2(1,4,4)) = true
*/

import java.util.ArrayDeque
fun main() {
    println(isInLastDigit(1,4,4))
    println(isInLastDigit2(1,4,4))
}

fun isInLastDigit(a : Int,b: Int,c: Int) : Boolean {
    val value = setOf(a % 10, b % 10, c % 10).size < 3
    return value
}

fun isInLastDigit2(a : Int, b : Int, c : Int) : Boolean{
    val array = intArrayOf(a ,b , c)
    val digits: IntArray = intArrayOf((a % 10), (b % 10), (c % 10))
    var stack = ArrayDeque<Int>()
    for (i in array) {
        var temp = i
        temp = temp / 10
        while(temp != 0)
        {
            stack.push(temp % 10)
            temp = temp / 10
        }
    }
    return (digits.intersect(stack).count() != 0)
}