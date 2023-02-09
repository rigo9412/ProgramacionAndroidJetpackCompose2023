fun main() {
    ingresarNumero(64)
}

fun ingresarNumero(value:Int?){
    if(value==null) return;
    var numeroNuevo:Int=0
    if(value%2==0){
       numeroNuevo=value/2
    } else {
        numeroNuevo=(value*3)+1
    }
    
    while (numeroNuevo!=1) {
	if(numeroNuevo%2==0){
       numeroNuevo=numeroNuevo/2
    }else{
        numeroNuevo=(numeroNuevo*3)+1
    }
	}
    
    if(numeroNuevo==1){
        println("La aplicacion termina porque llego a 1")
    }

}