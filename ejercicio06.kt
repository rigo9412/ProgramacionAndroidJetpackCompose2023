/*Elaborar una función que reciba 3 números enteros y que determine si el último
dígito del número se repite entre alguno de los 3 números.
 */

fun main() {
   print(determinarNumero(15,17,22))
}

fun determinarNumero(n1:Int,n2:Int,n3:Int): String {
    val result = setOf(n1%10, n2%10, n3%10).size == 3
    if (result){
        return "El último digito no se repite"
    }else{
        return "El último digito se repite"
    }
}