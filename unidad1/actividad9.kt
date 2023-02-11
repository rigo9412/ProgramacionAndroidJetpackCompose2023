import javax.xml.stream.events.Characters


fun main(){
    //println("Pasos realizados: "+Operacion(10_017_019_990_047_100))
    println("Pasos realizados: "+Operacion2(10_017_019_990_047_100))
    //println("Pasos realizados: "+Operacion3(10_017,6))
}

var pasos = 0
var pares = 0
var impares = 0
var numeros = mutableListOf<Any>()

fun Operacion(Original: Long): Long{
    var nuevo = Original
    if(Original != 1L){
        if(Original.mod(2) == 0){
            numeros.add(Original)
            pasos++
            nuevo = (Original / 2)
            Operacion(nuevo)
            return pasos.toLong()
        }else{
            numeros.add(Original)
            pasos++
            nuevo = (Original * 3) + 1
            Operacion(nuevo)
            return pasos.toLong()
        }
    }else{
        numeros.add(Original)
        pasos++
        println(numeros.toString())
        return pasos.toLong()
    }
    return pasos.toLong()

}


fun Operacion2(Original: Long): Long{
    var nuevo = Original
if(Original != 1L){
    if(Original.mod(2) == 0){
        numeros.add(Original)
        pares++
        pasos++
        nuevo = (Original / 2)
        Operacion2(nuevo)
        return pasos.toLong()
    }else{
        numeros.add(Original)
        impares++
        pasos++
        nuevo = (Original * 3) + 1
        Operacion2(nuevo)
        return pasos.toLong()
    }
}else{
    numeros.add(Original)
    pasos++
    impares++
    println(numeros.toString() + "\nPares: "+pares.toString()+"\n Impares: "+impares.toString())
    return pasos.toLong()
}
    return pasos.toLong()

}


fun Operacion3(Original: Long, num : Int): Long{

    var nuevo = Original

    var filtro = Original.toString()

    var numero:Char = Character.forDigit(num, 10)

    var verifica = filtro.none { it in numero .. numero }

    if (verifica != true){
        numeros.add(Original)
        println(numeros.toString())
        pasos++
        print("Se encontro el numero buscado en el paso: ")
        return pasos.toLong()
    }

        if(Original != 1L){
            if(Original.mod(2) == 0){
                numeros.add(Original)
                pares++
                pasos++
                nuevo = (Original / 2)
                Operacion3(nuevo,num)
                return pasos.toLong()
            }else{
                numeros.add(Original)
                impares++
                pasos++
                nuevo = (Original * 3) + 1
                Operacion3(nuevo,num)
                return pasos.toLong()
            }
        }else{
            numeros.add(Original)
            pasos++
            impares++
            println(numeros.toString() + "\nPares: "+pares.toString()+"\n Impares: "+impares.toString())
            return pasos.toLong()
        }

    return pasos.toLong()

}


