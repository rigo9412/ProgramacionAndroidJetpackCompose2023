//Elaborar una función que calcule el total a partir de un descuento dado.

fun main (){
    println(calcularDescuento("1000","25"))
}
fun calcularDescuento(subTotal: String,descuento:String): String?{
    
    var sub = subTotal.toDouble() 
    var des = descuento.toDouble()
    var total = sub - (sub * (des / 100)) //aplicamos el descuento    
    return total.toString() 
  
   
   
} 
