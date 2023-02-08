fun main() {
    val res = CalcularRepetidos(512,423,315)
    println(res)
}

fun CalcularRepetidos(V1: Int, V2: Int, V3: Int): Boolean{
    
    var strValor1 = V1.toString()
    var strValor2 = V2.toString()
    var strValor3 = V3.toString()

    var Array1 = strValor1.toCharArray()
    var Array2 = strValor2.toCharArray()
    var Array3 = strValor3.toCharArray()
    
    var UltimosDig = arrayOf(Array1.last(),Array2.last(),Array3.last())
    
    var Tamano = Array1.size
    Array1[Tamano-1] = 'v'
    Tamano = Array2.size
    Array2[Tamano-1] = 'v'
    Tamano = Array3.size
    Array3[Tamano-1] = 'v'
	
    val ArrayCompleto = Array1 + Array2 + Array3
    
    var BANDERA = 0
    
    for(i in 0..2){
        
        for(x in ArrayCompleto.indices){
            
            if(UltimosDig[i] == ArrayCompleto[x]){
                BANDERA++
                break             
            }  
        }
    }
         if(BANDERA == 3){
               
            return true
               
        }else{
               
            return false   
        }
}