/*Modificar la función anterior para que reciba un segundo número entero y
buscar si existe dentro de los pasos para llegar al número 1 y si lo encuentra,
detener el proceso e indicar en que paso lo encontró.
 */

fun main() {
   print(incisob(10_017_019_990_047_100,1132))
}

fun incisob(NumInicial : Long,Num : Long): String {
    var N = NumInicial ; var i = 1.toLong()
    var pasos = 1
    if(N == Num) return "SE ENCONTRO EN EL NUMERO "+Num+" EN EL PASO:"+pasos
    else {
        while (N != i){
            if (N%2 == i){
                N = (N*3)+1
                pasos++
        }
            N = N/2
            pasos++
            if(N == Num) return "SE ENCONTRO EN EL NUMERO "+Num+" EN EL PASO:"+pasos
    }
    }
    return "NO SE ENCONTRO EN EL NUMERO "+Num
}