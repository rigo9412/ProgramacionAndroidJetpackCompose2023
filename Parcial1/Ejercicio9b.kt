fun main() {
    var res = LlegarHasta1(10_017_019_990_047_100,11272)
    if(res == true){
        print("Se encontro el numero en el paso: "+Pasos)
    }else{
         print("No se encontro el numero")
    }
}
 //Variables Globales

	var Pasos = 0
	var Bandera = false

fun LlegarHasta1 (Valor: Long,Encontrar: Long): Boolean{ 	
    Pasos++
    
    if(Valor == Encontrar){
        Bandera = true
        return true
    }
    
    if(Valor == 1L){
        return false
    }
    
    var ParImpar = Valor%2
	
    if(ParImpar == 0L){
        
        var ValorNuevo = Valor/2
        LlegarHasta1(ValorNuevo,Encontrar)
    }else{
        
		var ValorNuevo = (Valor*3) +1
        LlegarHasta1(ValorNuevo,Encontrar)
    }
    
    return Bandera
}