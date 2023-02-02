fun main() {
    print(descuento(100.0,101.0))
}

fun descuento(value: Double, dis: Double): Any {
    if(dis > 100.0) return "Descuento no valido";
    return value - ((dis * value)/100);
}