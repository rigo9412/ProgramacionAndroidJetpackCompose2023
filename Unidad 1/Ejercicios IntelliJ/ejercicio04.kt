//Elaborar una función que calcule el total a partir de un descuento dado

fun main(){
    println(calcularDescuento(1000.0,10.0)) 
}
fun calcularDescuento(subtotal: Double,descuento: Double): Double?{ 
    var total = subtotal - (subtotal * (descuento / 100))
    return total
}