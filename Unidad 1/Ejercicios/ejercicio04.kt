//19100140 Ejercicio 4
//Elaborar una función que calcule el total a partir de un descuento dado.
//

fun main()
{
    print(aplicarDescuento(3.0,10.0))
}


fun aplicarDescuento(subtotal : Double ,descuento:Double) : Double
{
    var desc=descuento
    if(descuento >= 1){
         desc = descuento / 100.0
    }
    var total = subtotal * (1 - desc)
    return total
}