/*10. Elaborar una función que reciba una operación aritmética en formato texto y que la
resuelva, la operación solicitada son suma, resta, división, producto, residuo y
porcentaje.
a. Agregar funcionalidad de jerarquía de operadores
b. Agregar funcionalidad de raíz cuadrada */
import java.util.ArrayDeque

fun main(){
    print(evaluarExpresion("-1 + 2"))
}

fun evaluarExpresion(expresion : String) : Double{
    return EvaluarExpresionPostfija(ConvertirPostfijo(SepararExpresion(expresion)))
}

fun EvaluarExpresionPostfija(postfijo : ArrayDeque<String>) : Double{
    var pila = ArrayDeque<Double>()
    val operadores = mapOf("+" to { a: Double, b: Double -> a + b },
                           "-" to { a: Double, b: Double -> a - b },
                           "*" to { a: Double, b: Double -> a * b },
                           "/" to { a: Double, b: Double -> a / b },
                           "M" to { a: Double, b: Double -> a % b },
                           "%" to { a: Double, b: Double -> a * b / 100 },
                           "S" to { a: Double, _: Double -> Math.sqrt(a) },
                           "^" to { a: Double, b: Double -> Math.pow(a, b) })

    while(postfijo.size > 0){
        var token = postfijo.removeLast()

        if (token in operadores) {
            if(pila.size == 1 && token == "-"){
                pila.push(pila.pop() * -1)
                continue
            }
            val b = if(token == "S") 0.0 else pila.pop()
            val a = pila.pop()
            if (token == "/" && b == 0.0) {
                throw ArithmeticException("División por cero")
            }
            if (token == "S" && a < 0) {
                throw ArithmeticException("Raíz cuadrada de un número negativo")
            }
            pila.push(operadores[token]!!(a, b))
        } else {
            pila.push(token.toDouble())
        }
    }
    return pila.pop()
}

fun SepararExpresion(expr : String) : ArrayDeque<String>{
    var expresion = expr + " "
    var counter = 0
    var auxstr = ""
    var tokens = ArrayDeque<String>()

    while(counter < expresion.length){
        var character = expresion[counter]

        if(character.isDigit() || character == '.'){
            auxstr += character
        }
        else{
            if(character == ' ' && auxstr != ""){
                tokens.push(auxstr)
                auxstr = ""
            }
            else if(character != ' '){
                if(auxstr != ""){
                    tokens.push(auxstr)
                    auxstr = ""
                }
                tokens.push(character.toString())
            }
        }
        counter += 1
    }
    return tokens
}

fun ConvertirPostfijo(expresion : ArrayDeque<String>) : ArrayDeque<String>{
    val prioridad = mapOf("+" to 1, "-" to 1, "*" to 4, "/" to 4, "^" to 5, "M" to 4, "%" to 4, "S" to 3,"(" to 6,")" to 6)
    var operadores = ArrayDeque<String>()
    var resultado = ArrayDeque<String>()

    while(!expresion.isEmpty()){
        var token = expresion.removeLast()

        //Si el caracter no es un operador, empujarlo al stack de resultado
        if(!prioridad.contains(token)){
            resultado.push(token)
        }
        else if(token == "("){
            //Si el caracter es el operador ( entonces empujar al stack de operadores
            operadores.push(token)
        }
        else if(token == ")"){
            //Si el caracter es el operador ) hacer pop de todos los operandos que esten entre parentesis hacia el
            //stack de resultado
            while(operadores.size > 0 && operadores.peek() != "("){
                resultado.push(operadores.pop())
            }

            //Si no se encuentra el parentesis correspondiente la expresion es invalida
            if(operadores.size > 0 && operadores.peek() != "("){
                throw IllegalArgumentException("Expresion invalida")
            }
            else{
                operadores.pop()
            }
        }
        else{ //ELSE, el token es un operador
            //While el stack de operadores no este vacio y la prioridad del operador actual sea mayor o 
            //igual al ultimo del stack de operadores
            while(operadores.size > 0 && prioridad.getValue(token) >= prioridad.getValue(operadores.peek())){
                resultado.push(operadores.pop())
            }

            operadores.push(token)
        }
    }
    
    while(operadores.size > 0){
        resultado.push(operadores.pop())
    }

    return resultado
}