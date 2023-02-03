
fun main(){
    println(total().toString())
}

fun total():Double{
    println("Favor de ingresar el monto total base: ")
    var montoTotal = readln().toDouble()
    println("Favor de ingresar el descuento a aplicar: ")
    var descuento = readln().toDouble()
    return ((montoTotal/100)*descuento)
}

