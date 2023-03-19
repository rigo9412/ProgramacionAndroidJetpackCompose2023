/*Elaborar una función que reciba 3 números enteros y que determine si el último
dígito del número se repite entre alguno de los 3 números.
 */

fun main() {
   print(determinarNumero2(64,71,5962426))
}

fun determinarNumero1(n1:Int,n2:Int,n3:Int): Boolean {
    val result = setOf(n1%10, n2%10, n3%10).size != 3
    return result
}

fun determinarNumero2(n1:Int,n2:Int,n3:Int): Boolean {
    val num1 = (n1%10).toString()
    val num2 = (n2%10).toString()
    val num3 = (n3%10).toString()
    val isExist1 = num1 in n2.toString() || num1 in n3.toString() || num1 in n1.toString().dropLast(n1.toString().count())
    val isExist2 = num2 in n1.toString() || num2 in n3.toString() || num2 in n2.toString().dropLast(n2.toString().count())
    val isExist3 = num3 in n2.toString() || num3 in n2.toString() || num3 in n3.toString().dropLast(n3.toString().count())
    return (isExist1 ||isExist2 ||isExist3)
}