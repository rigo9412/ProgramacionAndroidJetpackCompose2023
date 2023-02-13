fun main(){

    println(reduccionAUnoA(7))

}


fun reduccionAUno(n: Long) {
    var numero = n
    println(numero)

    while (numero != 1.toLong())
    {
        numero = if (numero % 2.toLong() == 0.toLong())
        {
            numero / 2.toLong()
        }
        else
        {
            numero * 3.toLong() + 1.toLong()
        }
        println(numero)
    }

}



fun reduccionAUnoA(n: Long): String {
    var numero = n
    var pasos = 0.toLong()
    var par = 0.toLong()
    var impar = 0.toLong()

    while (numero != 1.toLong())
    {

        if (numero % 2 == 0.toLong())
        {
            numero /= 2
            par++
        }
        else
        {
            numero = numero * 3 + 1
            impar++
        }
        pasos++
    }

    var res = Pair(pasos, Pair(par, impar))

    return ("PASO=" + res.first.toString() + ",PARES="+res.second.first.toString()+",IMPARES="+res.second.second.toString())
}



fun reduccionAUnoB(n: Long, secundario: Long): String {

    var numero = n
    var pasos = 0.toLong()
    var par = 0.toLong()
    var impar = 0.toLong()

    while (numero != 1.toLong())
    {

        if (numero == secundario)
        {
            return Pair(pasos, Pair(par, impar)).toString()
        }

        if (numero % 2 == 0.toLong())
        {
            numero /= 2
            par++
        }
        else
        {
            numero = numero * 3 + 1
            impar++
        }
        pasos++
    }

    var res = Pair(pasos, Pair(par, impar))
    return Pair(pasos, Pair(par, impar)).toString()
}