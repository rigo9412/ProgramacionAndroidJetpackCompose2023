fun main() {
    //Llamar a la funcion Calcular, con parametros
    Calcular(1600.0,20.0)
}

fun  Calcular(Cantidad: Double, Descuento: Double){
    
    //Calcular porcentaje
    var res = (Cantidad / 100) * Descuento
    print(res)
}