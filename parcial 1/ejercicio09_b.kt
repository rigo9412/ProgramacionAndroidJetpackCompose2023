//Modificar la función anterior para que reciba un segundo número entero y
//buscar si existe dentro de los pasos para llegar al número 1 y si lo encuentra,
//detener el proceso e indicar en que paso lo encontró.

fun main() {
    print(ingresarNumeroB(10_017_019_990_047_100,11272))
}

fun ingresarNumeroB(value:Long, value2:Long):String{ 
    var contador=1
    var par=0
    var impar=1
    var numeroNuevo=value
    if(value%2==0.toLong()){
       numeroNuevo=numeroNuevo/2
        contador++
        par++
    } else {
        numeroNuevo=(numeroNuevo*3)+1
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
    if(numeroNuevo==value2){
       return "SE ENCONTRO EN EL NUMERO $value2 EN EL PASO:$contador"
    }
	}
    return "NO SE ENCONTRO EN EL NUMERO $value2"
}