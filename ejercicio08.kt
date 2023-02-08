//Cree una función que tome dos o más arrays y devuelva una array de sus diferencias.
//La array que se devuelve debe contener solo valores únicos (no duplicados).


fun main() {
   val lista1 = listOf(1,2,3,4,0,0,"a","null")
   val lista2 = listOf("2",1,0,"A",9,"NULL")
   noDuplicados(lista1,lista2)
}

fun <T> noDuplicados(vararg array: List<T>) {
    var list = ArrayList<T>()
    var x = 0
    var index = 0
    while(index < array.size){
        while(x < array.get(index).size)
        {
        	list.add(array.get(index).get(x))
        	x++
    	}
        index++
        x=0
    }
    val noDuplicados = list.distinct()
    print(noDuplicados)
}