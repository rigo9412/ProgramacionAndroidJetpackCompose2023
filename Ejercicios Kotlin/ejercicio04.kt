// Elaborar una función que calcule el total a partir de un descuento dado.
fun calcularTotal(total: Double, descuento: Double): Double {
    return total - (total * descuento / 100)
}

fun main(args: Array<String>) {
    println("El total es: ${calcularTotal(500, 10.0)}")
}