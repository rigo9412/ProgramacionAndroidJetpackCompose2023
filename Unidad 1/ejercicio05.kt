fun main() {

    println("Ingrese texto a desglosar:")
    contar(readLine().toString())
}

fun contar(value: String){

    var x=value.split("")
    var letras: Int=0
    val letrasOriginales:MutableList<String> = mutableListOf()
    val letrasIndividuales:MutableList<String> = mutableListOf()
    //Lista mutable arriba,
    //para agregarle cosas despues

    //Recorremos nuestro texto hecho split
    for (letra in x){
        //Preguntamos que no haya "blancos", ni espacios
        if (letra!=""&& letra!=" ") {
            letrasOriginales.add(letra)
            letras++
        }
    }


    letrasIndividuales.addAll(letrasOriginales)//igualamos arreglos para preguntar repetidos
    var i: Int=0//variable auxiliar para remover duplicados
    var duplicado = 0

    //Ahora toca genera una lista sin repetidos para preguntar en ella.
    for (letraOriginal in letrasOriginales)
    {
        while (i<=letrasIndividuales.size-1)//recorremos la lista de individuales buscando duplicados
        {
            if (letrasIndividuales[i] == letraOriginal) {
                //var letrita=letrasIndividuales[i]
                //println("Letra encontrada: $letrita")
                duplicado++
            }
            if(duplicado>=2){//si se repite dos o mas veces la letra esta duplicada
                letrasIndividuales.removeAt(i)// y la quitamos de su posicion
                duplicado=1//igualamos a uno porque ya encontramos el duplicado y fue removido
            }
            i++
        }
        //Reiniciamos variables
        i=0
        duplicado=0
    }

    println(letrasIndividuales)//imprimimos nuestra lista sin repetidos
    i=0

    var repetido:Int=0
    for (letraIndividual in letrasIndividuales)//volvemos a recorrer preguntando si aparece en nuestra oracion
    {
        for(letraOriginal in letrasOriginales)
        {
             if (letraIndividual==letraOriginal)
             {
              repetido++
             }
        }
        println("La letra '$letraIndividual' se repitio $repetido")//imprimimos
        repetido=0

    }
}