/*10. Elaborar una función que reciba una operación aritmética en formato texto y que la
resuelva, la operación solicitada son suma, resta, división, producto, residuo y
porcentaje.
a. Agregar funcionalidad de jerarquía de operadores
b. Agregar funcionalidad de raíz cuadrada.*/


fun main() {
    convertirCadena("45+8-8")
}

fun convertirCadena(operacion:String){
    var guardarNumeros=ArrayList<Int>()
    var cadena=operacion.toList()
    var i=0
    var numero=""
    var primerNumero=0.0
    var segundoNumero=0.0
    var resultado=0.0
    var o=0
    var resultadoFinal=0.0

    while (i < operacion.length){
        
         if(operacion[i].isDigit()){
             numero=numero+operacion[i]
             i++
         }
         else{
             guardarNumeros.add(numero.toInt())
             numero=""
             i++
         }
         
         
    }
    if(numero!="")
        {
         guardarNumeros.add(numero.toInt())   
         numero=""
        }
    val (digits, operador) = cadena.partition { it.isDigit() }
    
    for (g in guardarNumeros.indices){
        if(primerNumero==0.0){
            primerNumero=guardarNumeros[g].toDouble()
        }
        else if(segundoNumero==0.0){
            segundoNumero=guardarNumeros[g].toDouble()
        }
        
        if(primerNumero!=0.0 && segundoNumero!=0.0){
            resultado=auxiliar(primerNumero,segundoNumero, operador[o])
            primerNumero=resultado
            segundoNumero=0.0
            o++
    }
    }
    print(resultado)
}



fun auxiliar(valor1:Double,valor2:Double,operador:Char):Double{
    return  when (operador) {
        '+' -> valor1 + valor2
        '-' -> valor1 - valor2
        '*' -> primerNumero * segundoNumero
        '/' -> primerNumero / segundoNumero
         'M' -> primerNumero % segundoNumero
         '%' -> primerNumero * (segundoNumero/100)
        '^' -> Math.sqrt(segundoNumero)
     else -> throw IllegalArgumentException("Error: Operador no válido")
        }
    
}