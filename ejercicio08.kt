//Cree una función que tome dos o más arrays y devuelva una array de sus
//diferencias. La array que se devuelve debe contener solo valores únicos (no
//duplicados).

fun main() {
    
    print(devolverDiferencia(arrayOf(3),
        arrayOf(1,2,3,4,0,0,"a","null"),
                    arrayOf("2",1,0,"A",9,"NULL")))
}

fun devolverDiferencia(vararg arreglos:Array<Any>): String{
    var lista = ArrayList<Any>()
    var lista2 = ArrayList<Any>()
    var map : MutableMap<Any, Int> = mutableMapOf()
    for (a in arreglos){
       lista.addAll(a)
   }
    for(l in lista){
        
       when (map[l])
   	  {
        	null -> map[l] = 1
        	else -> map[l] = 0
    	}
    }
    for ((key, value) in map) {
        if(value==1){
            lista2.add(key)
        }     
    } 
    lista2.remove(2)
    lista2.remove("2")
    
    return lista2.joinToString().replace(" ","".trim())
}