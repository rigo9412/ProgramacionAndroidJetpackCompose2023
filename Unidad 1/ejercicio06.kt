//Elaborar  una funcion que reciba tres numeros enteros y determine si el ultimo digito
//se repite entre alguno de los tres numeros

import java.text.Normalizer

fun main() {
    contarUnidades(13,255,20)
}

fun contarUnidades(a:Int,b:Int,c:Int):Boolean{
    //arreglos con los ultimos elementos
    var ultimos:Array<String> = arrayOf(a.toString().takeLast(1),b.toString().takeLast(1),c.toString().takeLast(1))
    var numerosPegados:String=a.toString().dropLast(1)+b.toString().dropLast(1)+c.toString().dropLast(1)
    var x=numerosPegados.split(""," ")
    var numerosSeparados:MutableList<String> = mutableListOf()
    for (i in x){
        if (i!=""&&i!=" "){
            numerosSeparados.add(i)
        }
    }
    println("Numeros pegados: $numerosPegados")
    for(i in ultimos) {
        println("Ultimos digitos: $i")
    }
    for (i in numerosSeparados){
        println("Numeros aparte: $i")
    }

    for (i in ultimos)
    {
        for (o in numerosSeparados)
        {
            if(i==o){
                println("Numero repetido: $i")
                return true
            }
        }
    }
    println("Numeros sin repetir")
    return false

}