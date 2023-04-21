//Elaborar una función que aplica las siguientes reglas, ingresar un número entero N
//positivo y si es N es par lo dividimos y si es impar lo multiplicamos por 3 y le sumamos 1, esta función se deberá detener cuando llegue a 1.

fun main() {
    print(ingresarNumero(10_017_019_990_047_100))
}

fun ingresarNumero(value:Long):Long{ 
    if(value==1.toLong()){
        return value
    }
    var numeroNuevo:Long=0
    if(value%2==0.toLong()){
       numeroNuevo=value/2
    } else {
        numeroNuevo=(value*3)+1
    }
    
    while (numeroNuevo!=1.toLong()) {
	if(numeroNuevo%2==0.toLong()){
       numeroNuevo=numeroNuevo/2
    }else{
        numeroNuevo=(numeroNuevo*3)+1
    }
	}
    return numeroNuevo
}