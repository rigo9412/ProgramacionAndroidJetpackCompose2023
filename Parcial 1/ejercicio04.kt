//Elaborar una función que calcule el total a partir de un descuento dado.

fun main()
{
    print(descuentoTotal(2.5,10.0))
}


fun descuentoTotal(amount:Double,discount:Double) : Double
{
    return amount - (amount * (discount / 100));
}