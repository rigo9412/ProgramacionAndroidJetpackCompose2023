//Elaborar una función que reciba un array de enteros y que invierta la primera
//posición por la segunda posición.
fun main() {
    var arreglo=arrayOf(5,89,12,45)
    recibirArreglo(arreglo)
}

fun recibirArreglo(arreglo:Array<Int>){
    var datoAlmacenado=0
    var datoAlmacenado2=0
    for(a in arreglo.indices){
        if(a==0){
            datoAlmacenado=arreglo[a]
        }else if(a==1){
            datoAlmacenado2=arreglo[a]
        }
    }
    
    for(a in arreglo.indices){
        if(a==0){
            arreglo[a]=datoAlmacenado2
        }else if(a==1){
            arreglo[a]=datoAlmacenado
        }
    }
    
    for(a in arreglo.indices){
        println(arreglo[a])
    }
}