fun main(){
    if(RecibirNumeros(103,27,5))
    {
        println("Se repiten")
    }
    else
    {
        println("No hay numeros repetidos")
    }
}

fun RecibirNumeros(n1:Int, n2:Int, n3:Int):Boolean{
    //convierte los enteros a string
    val s1 = n1.toString(); val s2 = n2.toString(); val s3 = n3.toString();

    //se hace un array de cada numero
    var array1 = s1.map { it.toString().toInt() }.toIntArray()
    var array2 = s2.map { it.toString().toInt() }.toIntArray()
    var array3 = s3.map { it.toString().toInt() }.toIntArray()

    //este array contendrá los ultimos digitos de cada numero
    var array4 = emptyArray<Int>()
    //este otro contendrá los digitos restantes
    var array5 = emptyArray<Int>()

    //extraer los ultimos digitos de cada numero
    array4 += array1.last()
    array1 = array1.dropLast(1).toIntArray()

    array4 += array2.last()
    array2 = array2.dropLast(1).toIntArray()

    array4 += array3.last()
    array3 = array3.dropLast(1).toIntArray()

    //juntar los digitos restantes de cada numero en un mismo array
    for(c1 in array1)
    {
        array5 += c1
    }
    for(c2 in array2)
    {
        array5 += c2
    }
    for(c3 in array3)
    {
        array5 += c3
    }

    /*println("arreglo 1")
    for (c in array4)
    {
        println(c)
    }
    println("arreglo 2")
    for (c in array5)
    {
        println(c)
    }*/

    var bln = false
    for(a1 in array4)
    {
        for(a2 in array5)
        {
            if(a1 == a2)
            {
                bln = true
                //println("El número $a1 se repite")
            }
        }
    }
    if(bln == false)
    {
        //println("No hay numeros repetidos")
    }
    return bln
}