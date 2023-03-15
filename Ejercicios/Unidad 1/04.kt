////EJERCICIO 4
////Elaborar una función que calcule el total a partir de un descuento dado.

val descuento = 0.1 //%10
fun calcularDescuento(subTotal: Double): Double {
    return subTotal - subTotal * descuento
}

fun main() {
    println("Subtotal: ${500}. Descuento: %${descuento * 100}. Total: ${calcularDescuento(500.toDouble())}");
}