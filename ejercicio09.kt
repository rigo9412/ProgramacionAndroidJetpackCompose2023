//Elaborar una función que aplica las siguientes reglas, ingresar un número entero N
//positivo y si es N es par lo dividimos y si es impar lo multiplicamos por 3 y le
//sumamos 1, esta función se deberá detener cuando llegue a 1.
//a. Modificar la función anterior y contar el número de pasos que le tomo llegar a
//1, contar los números pares e impares.
//b. Modificar la función anterior para que reciba un segundo número entero y
//buscar si existe dentro de los pasos para llegar al número 1 y si lo encuentra,
//detener el proceso e indicar en que paso lo encontró.
fun main(){
    StepsCounter(10_017_019_990_047_100,11272)
}

fun StepsCounter(n:Long,targ:Long){
    var steps = 1L
    var even = 0
    var odd = 1L
    var num:Long= n

    while(num != 1L){
        if(num==targ){
            println("El target: $targ se encontró en el paso: $steps")
            break
        }
        if(num % 2 ==0L){
            num/=2
            even++
        }else{
            num=num*3+1
            odd++
        }
        steps++
    }
        if(num==1L){
            println("El target: $targ no se encontró")
        }
    println("PASOS: $steps  PARES: $even IMPARES: $odd")
}