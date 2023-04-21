//Elaborar una función que reciba 3 números enteros y que determine si el último
//dígito del número se repite entre alguno de los 3 números.

//Elaborar una función que reciba 3 números enteros y que determine si el último
//dígito del número se repite entre alguno de los 3 números.

fun main() {
    print(determinarRepeticion(5,1,21))
}

fun determinarRepeticion(numero1:Int, numero2:Int,numero3:Int): Boolean{
    val n1=numero1.toString().last()
    val n2=numero2.toString().last()
    val n3=numero3.toString().last()
    return (n1==n2||n2==n3||n1==n3||n2==n3)

}