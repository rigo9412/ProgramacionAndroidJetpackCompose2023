fun main(){
    val num : Long = 10_017_019_990_047_100
    println(HacerOperaciones(num))
    println(ContarPasos(num))
    println(BuscarNumero(num,55))
    println(BuscarNumero(num,num))
    println(BuscarNumero(num,11272))

    
}

fun HacerOperaciones(n1 : Long):Long{
    var n = n1
    while(n!=1L)
    {
        if(n%2 == 0L)
        {
            n = n / 2
        }
        else{
            n = (n*3) + 1
        }
        //println(n)
    }
    return n
}

fun ContarPasos(n1:Long):String{
    var n = n1; var pares = 0; var impares = 0; var pasos = 1;
    while(n!=1L)
    {
        if(n%2 == 0L)
        {
            pares++
            n = n / 2
        }
        else{
            impares++
            n = (n*3) + 1
        }
        pasos++
        //println(n)
    } 
    impares++ //para contar el numero 1
    return "PASO=$pasos,PARES=$pares,IMPARES=$impares"
}

fun BuscarNumero(n1:Long, n2:Long):String{
    var n = n1; var pasos = 1; var encuentra = false
    while(n!=1L)
    {
        if(n == n2)
        {
            encuentra = true
            break;
        }
        if(n%2 == 0L)
        {
            n = n / 2
        }
        else{
            n = (n*3) + 1
        }
        pasos++
        //println(n)
    }
    if(encuentra)
    {
        return "SE ENCONTRO EN EL NUMERO $n2 EN EL PASO:$pasos"
    }
    else{
        return "NO SE ENCONTRO EN EL NUMERO $n2"
    }
}