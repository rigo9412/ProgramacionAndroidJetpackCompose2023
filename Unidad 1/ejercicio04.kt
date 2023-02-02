//NOTA, programa hecho en IntelliJ para poder hacer los inputs
// Elaborar una funcion que calcule el total
// a partir de un descuento dado
fun main(args: Array<String>) {
    descontar()
}

fun descontar(){
    println("Ingrese el costo:")
    var costo: Double= readLine().toString().toDouble()
    println("Igrese el descuento:")
    var descuento:Double = readLine().toString().toDouble()
    var costoTotal = costo-costo*(descuento*0.01)
    println("El costo con descuento es de: $costoTotal")
}