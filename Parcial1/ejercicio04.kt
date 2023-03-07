
//Elaborar una función que calcule el total a partir de un descuento dado.

fun main(){
  println(CheckDiscount(300.0,15))
}

fun CheckDiscount(price:Double,discount:Int): Double{
    return price - (discount * price/100) 
}
