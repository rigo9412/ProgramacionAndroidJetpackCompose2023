/*b.Modificar la función anterior para que reciba un segundo
número entero ybuscar si existe dentro de los pasos para llegar al número 1
y si lo encuentra,detener el proceso e indicar en que paso lo encontró.*/

fun main(args: Array<String>) {
    println(aplicarRegla(10,20))
}

fun aplicarRegla(numero1: Int, numero2:Int): String{
    var N = numero1
    var auxiliar = 1
    var pasos = 1
    while (N != auxiliar) {
        if (N % 2 == auxiliar) {
            N = (N * 3) + 1
            pasos = pasos + 1
        } else
            N /= 2
        pasos = pasos + 1
        if(N == numero2) return "el numero "+numero2+" aparece en el paso: #"+pasos
    }
    return ("No se encontro el numero  $numero2")
}
