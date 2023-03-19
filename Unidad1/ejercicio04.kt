/*Elaborar una función que calcule el total a partir de un descuento dado.
 */

fun main()
{
    print(calcularTotal(500.0,10.0))
}

fun calcularTotal(subtotal : Double ,descuento: Double) : Double
{
    var descu = 0.0
    if(descuento >= 1){
         descu = (subtotal*descuento)/100
    }
    return (subtotal - descu)
}