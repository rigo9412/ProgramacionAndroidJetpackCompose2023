//Elaborar una función que reciba un array de enteros y
//que invierta la primeraposición por la segunda posición.
fun main(args: Array<String>) {
    print(invertirOrden(12,42,52,52,67))
}

fun invertirOrden(vararg array: Int): List<Int> {
    var lista = ArrayList<Int>()
    var indice = 0

    while (indice < array.size){
        if(indice == 0){
            lista.add(array.get(1))
            indice++
        }
        else{
            if (indice == 1){
                lista.add(array.get(0))
                indice++
            }
            else{
              lista.add(array.get(indice))
                indice++
            }
        }
    }
    return lista
}