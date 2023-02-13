//a. Modificar la función anterior y contar el número de pasos que le tomo llegar a 1, contar los números pares e impares.

fun main() {
    print(ingresarNumeroA(10_017_019_990_047_100))
}

fun ingresarNumeroA(value:Long):String{ 
    var contador:Int=1
    var par=0
    var impar=0
    if(value==1.toLong()){
        return value.toString()
        contador++
    }
    var numeroNuevo:Long=0
    if(value%2==0.toLong()){
       numeroNuevo=value/2
        contador++
        par++
    } else {
        numeroNuevo=(value*3)+1
        contador++
        impar++
    }
    
    while (numeroNuevo!=1.toLong()) {
        contador++
	if(numeroNuevo%2==0.toLong()){
       numeroNuevo=numeroNuevo/2
        par++
    }else{
        numeroNuevo=(numeroNuevo*3)+1
        impar++
    }
	}
    return numeroNuevo.toString()+ "\nPASO=$contador,PARES=$par,IMPARES=$impar"
}
