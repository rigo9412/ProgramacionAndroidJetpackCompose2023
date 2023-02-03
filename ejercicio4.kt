fun main(){
    print(Descuento(1000.0,.8))
}

fun Descuento(precio: Double, descuento: Double): Double {
    var desc = precio * descuento
    var presfin = precio - desc
    return presfin
    
}