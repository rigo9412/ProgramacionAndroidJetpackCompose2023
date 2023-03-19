/*Modificar la función anterior y contar el número de pasos que le tomo llegar a
1, contar los números pares e impares.
 */
fun main() {
   print(inicisoa(10_017_019_990_047_100))
}

fun inicisoa(NumInicial : Long): String {
    var N = NumInicial ; var i = 1.toLong()
    var pasos = 1; var impar = 1 ;var par = 0
    while (N != i){
        if (N%2 == i){
            N = (N*3)+1
            impar++
            pasos++
        }
        N = N/2
        par++
        pasos++
    }
    return "PASO="+pasos+",PARES="+par+",IMPARES="+impar
}