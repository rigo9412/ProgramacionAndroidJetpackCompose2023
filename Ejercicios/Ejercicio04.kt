// Elaborar una función que calcule el total a partir de un descuento dado.
fun main(argumento: Array<String>) {
    println("Ingrese el precio total")
    val total = readln().toDouble()
    println("Ingrese el descuento en %")
    val porcentaje = readln().toDouble()
    val descuento = total * (porcentaje/100)
    val totalfinal = total - descuento
    println("El precio total con descuento es: $totalfinal")
}