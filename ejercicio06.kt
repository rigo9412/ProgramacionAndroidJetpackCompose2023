// Elaborar una función que reciba 3 números enteros y que determine si el último
// dígito del número se repite entre alguno de los 3 números

fun main() {

    // QUE SEAN INT Y SE CONVIERTAN EN STRING PARA JUGAR CON ELLOS
    var valor1 = 345
    var valor2 = 1
    var valor3 = 569
    

    separaryCoincidir(valor1,valor2,valor3)    
}

fun separaryCoincidir(valor1: Int,valor2: Int,valor3: Int){
    var contador1 = 0
    var contador2 = 0
    var contador3 = 0
    var y = ""
    var q = ""
    var l = ""
    var resultado = ""
    var resultado2 = ""
    var resultado3 = ""
    var resultadofinal = ""
    var s1 = ""
    var a1 = ""
    var d1 = ""
    var x = ""
    var m = ""
    var p = ""
    var s = valor1.toString()
    var a = valor2.toString()
    var d = valor3.toString()
    // usando bucle for simple
    for (i in s.length - 1 downTo 0) {
        if(contador1 == 0){
        	s1 = s[i].toString()
        	println(s1)
        	contador1 = contador1 + 1
            // println(contador1)
        }
        if(contador1 >= 2){
            x = s[i].toString()
            y = "$x"
            resultado = "$resultado$y"
        }
        contador1= contador1 + 1
    }
    print(resultado)


    for (i in a.length - 1 downTo 0) {
        if(contador2 == 0){
        	a1 = a[i].toString() 
        	println(a1)
        	contador2 = contador2 + 1
            // println(contador2)
        }
        if(contador2 >= 2){
            p = a[i].toString()
            q = "$p"
            resultado2 = "$resultado2$q"
        }
        contador2= contador2 + 1
    }
    print(resultado2)

    for (i in d.length - 1 downTo 0) {
        if(contador3 == 0){
            d1 = d[i].toString() 
        	println(d1)
        	contador3 = contador3 + 1
            // println(contador3)
        }
        if(contador3 >= 2){
            m = d[i].toString()
            l = "$m"
            resultado3 = "$resultado3$l"
        }
        contador3= contador3 + 1
    }
    print(resultado3)


    resultadofinal = "$resultado$resultado2$resultado3"
    for (i in resultadofinal.length - 1 downTo 0){
        if(resultadofinal[i].toString() == "$s1"){
            println("COINCIDE CON $s1")
        }
        if(resultadofinal[i].toString() == "$a1"){
            println("COINCIDE CON $a1")
        }
        if(resultadofinal[i].toString() == "$d1"){
            println("COINCIDE CON $d1")
        }
            

    }
}

