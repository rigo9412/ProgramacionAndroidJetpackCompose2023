/////EJERCICIO 6
/////Elaborar una función que reciba 3 números enteros y que determine si el último dígito del número se repite entre alguno de los 3 números.

fun buscar(n1: Int, n2: Int, n3: Int): Boolean {
    var ud1 = (n1 % 10).toString()
    var ud2 = (n2 % 10).toString()
    var ud3 = (n3 % 10).toString()
    
    var nn = (n1/10).toString() + (n2/10).toString() + (n3/10).toString()

    return (nn.contains(ud1) || nn.contains(ud2) || nn.contains(ud3)) ||//Caso 1
           (ud1 == ud2 || ud1 == ud3 || ud2 == ud3) //Caso 2
}

fun main() {
    println(buscar(8820380, 2347340, 723423471)) //true
    println(buscar(1,2,3))//false
    println(buscar(1,3,1))//true
}
