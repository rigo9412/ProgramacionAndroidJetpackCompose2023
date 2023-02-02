fun main(){
    var a : Double = 7500.0
    var b : Double = 15.0
    println("El total es : $" + CalcularDescuento(a,b))
}

fun CalcularDescuento(precio: Double, descuento: Double): Double{
    var desc : Double = (precio * descuento) / 100
    var total : Double = precio - desc
    return total
}