fun main(args: Array<String>) {
    var Array1 = arrayOf(1,2,3,4,0,0,"a","null")
    var Array2 = arrayOf(2,1,0,"A",9,"NULL")
	ObtenerUnion(Array1,Array2)
}

fun <T> ObtenerUnion(Arreglo1: Array<T>,Arreglo2: Array<T>){
	
    var ArgCombinados = Arreglo1 + Arreglo2 
  
   var lista = ArrayList<T>()
   var BANDERA = false
    var contador = 0
    
    for(x in ArgCombinados.indices){
        BANDERA = false
        
        for(i in ArgCombinados.indices){
        
            if(x == i){
				continue  
            }

            if(ArgCombinados[x] == ArgCombinados[i]){
                
                BANDERA = true
           		break
       		}
       }
        if(BANDERA == false){
            lista.add(ArgCombinados.get(x))
        }
    }
    print(lista)       
}
  