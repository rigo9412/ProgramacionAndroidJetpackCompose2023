//Elaborar una función que calcule el total a partir de un descuento dado.
fun main() {
    calcularTotal(352.2,25.0)
}

fun calcularTotal(cantidad: Double,descuento: Double){
    if(cantidad==null || descuento==null) return;
    var desCantidad=(cantidad*descuento)/100
    var total=cantidad-desCantidad
    print("El total es: "+total)
}