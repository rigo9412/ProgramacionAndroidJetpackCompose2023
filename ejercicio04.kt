//Elaborar una función que calcule el total a partir de un descuento dado.

fun main (args: Array<String>){

   var precio : Double
   var descuento : Double
   var precioFinal :Double
   var CalcularDescuento : Double

    print("Ingrese el Precio: ")
    precio = readLine()!!.toDouble()

    print("Ingrese el descuento: %")
    descuento =readLine()!!.toDouble()

    CalcularDescuento = descuento / 100
    descuento = precio * CalcularDescuento
    precioFinal = precio - descuento
    println("El precio final es igual a $precioFinal")
    println("El descuento es igual a $descuento")

}

