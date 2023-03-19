//19100140 Ejercicio 3
//Elaborar una función que reciba un texto y cuente el número de letras que contiene.
//

fun main()
{
    print(contarLetras("cuantos hay aqui 323"))
}


fun contarLetras(valor : String) : Int
{
    var lett = 0
    for (i in 0..valor.lastIndex){
        if(valor[i].isLetter()){
            lett++
        }
    }
    return lett
}