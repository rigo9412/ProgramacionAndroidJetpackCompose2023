//4.- Elaborar una funcion que calcule el total a partir de un descuento dado
fun main(){
    println(CalcularTotal(100.0,30.0))
}

fun CalcularTotal(subtotal : Double, descuento : Double) : Double {
    return (subtotal * (1-(descuento / 100)))
}