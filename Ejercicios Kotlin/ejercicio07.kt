//Elaborar una función que reciba un array de enteros y
//que invierta la primeraposición por la segunda posición.
fun main(args: Array<String>) {
    print(invertir(12,42,52,52,67))
}

fun invertir(vararg array: Int): List<Int> {
    val lista = ArrayList<Int>()
    var indice = 0

    while (indice < array.size){
        if(indice == 0){
            lista.add(array[1])
            indice++
        }
        else{
            if (indice == 1){
                lista.add(array[0])
                indice++
            }
            else{
                lista.add(array[indice])
                indice++
            }
        }
    }
    return lista
}