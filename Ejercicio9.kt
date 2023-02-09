fun main(args: Array<String>) {
    var res = LlegarHasta1(10_017_019_990_047_100)
    print(res)
}

fun LlegarHasta1 (Valor: Long): Long{
   	
    

    if(Valor == 1L){
        return 1
    }
	println(Valor)
    
    var ParImpar = Valor%2
	
    if(ParImpar == 0L){
        
        var ValorNuevo = Valor/2
        LlegarHasta1(ValorNuevo)
    }else{
		var ValorNuevo = (Valor*3) +1
        LlegarHasta1(ValorNuevo)
    }
    
    return 1
}