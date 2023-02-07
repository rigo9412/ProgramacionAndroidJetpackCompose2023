/////EJERCICIO 6
/////Elaborar una función que reciba 3 números enteros y que determine si el último dígito del número se repite entre alguno de los 3 números.

fun buscar(n1: Int, n2: Int, n3: Int): Boolean {
    var ud1 = (n1 % 10).toString()
    var ud2 = (n2 % 10).toString()
    var ud3 = (n3 % 10).toString()
    
    var nn1 = (n1/10).toString()
    var nn2 = (n2/10).toString()
    var nn3 = (n3/10).toString()

    return nn1.contains(ud1) || nn1.contains(ud2) || nn1.contains(ud3) ||
            nn2.contains(ud1) || nn2.contains(ud2) || nn2.contains(ud3) ||
            nn3.contains(ud1) || nn3.contains(ud2) || nn3.contains(ud3)
}

fun main() {
    println(buscar(8820380, 2347340, 723423471)) //true
    println(buscar(1,2,3))//false
}
