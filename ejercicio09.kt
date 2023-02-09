// Elaborar una función que aplica las siguientes reglas, ingresar un número entero N
// positivo y si es N es par lo dividimos y si es impar lo multiplicamos por 3 y le
// sumamos 1, esta función se deberá detener cuando llegue a 1.

// a. Modificar la función anterior y contar el número de pasos que le tomo llegar a
// 1, contar los números pares e impares.

// b. Modificar la función anterior para que reciba un segundo número entero y
// buscar si existe dentro de los pasos para llegar al número 1 y si lo encuentra,
// detener el proceso e indicar en que paso lo encontró.

fun main(){

    llegar1(20,2)
    
}

fun llegar1(num : Int, num2 : Int){
    var numero = num
    var pasos = 0
    var pares = 0
    var impares = 0
    var topar = num2
    var seTopo = 0 

    while (numero != 1) {
        println(numero)
        if(numero % 2 == 0){
            numero = numero/2 
            pares++  
        }else { 
            numero = 3*numero+1   
            impares++
        }

        if(numero == topar){
            
            seTopo = 1
            break
        }   
        pasos++
    }

    pasos++
    
    if(seTopo == 1){
        println(numero)
        if(numero % 2 == 0){
            pares++  
        }else{
            impares++
        }
        pasos++
        println("PARES: $pares")
        println("IMPARES: $impares")
        println("PASOS: $pasos")
        println("Se topo con $topar")
    }else{
        println(numero)
        if(numero % 2 == 0){
            pares++  
        }else{
            impares++
        }
        println("PARES: $pares")
        println("IMPARES: $impares")
        println("PASOS: $pasos")
        println("NO SE TOPO CON NINGUN $topar")
    }

}
