//19100140 Ejercicio 5
//Elaborar una función que reciba un texto y cuente cuantas veces aparece en el texto solo letras, ejemplo a=5, o=40.
//

fun main()
{
    print(conteoLetras("Hola mundo "))
}


fun conteoLetras(texto: String): Map<Char, Int> {
    val lett = mutableMapOf<Char, Int>()
    for (char in texto) 
    {
        if (lett.containsKey(char)) 
        {
            lett[char] = lett[char]!! + 1
        } 
        else 
        {
            lett[char] = 1
        }
    }
    return lett
}