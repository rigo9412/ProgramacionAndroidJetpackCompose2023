
fun main() {
    var res = LlegarHasta1(10_017_019_990_047_100)
    //print(res)
    println("PAR = "+Par)
    println("IMPAR = "+Impar)
    print("PASOS = "+Pasos)
}
 //Variables Globales
 	var Par = 0
 	var Impar = 0
	var Pasos = 0

fun LlegarHasta1 (Valor: Long): Long{ 	
    Pasos++
    
    if(Valor == 1L){
        Impar++
        return 1
    }
    var ParImpar = Valor%2
	
    if(ParImpar == 0L){
        
        Par++
        var ValorNuevo = Valor/2
        LlegarHasta1(ValorNuevo)
    }else{
        
        Impar++
		var ValorNuevo = (Valor*3) +1
        LlegarHasta1(ValorNuevo)
    }
    return 1
}