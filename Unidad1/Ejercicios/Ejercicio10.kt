import java.util.ArrayDeque

fun main(){
    print (Evaluar(Convertir(Expresion("(-1 + 2)+(10*2)"))))
}

fun Evaluar(postfijo : ArrayDeque<String>) : Double{
    var pila = ArrayDeque<Double>()
    val operadores = mapOf("+" to { a: Double, b: Double -> a + b },"-" to { a: Double, b: Double -> a - b },"*" to { a: Double, b: Double -> a * b },
                           "/" to { a: Double, b: Double -> a / b },"M" to { a: Double, b: Double -> a % b },"%" to { a: Double, b: Double -> a / b * 100 },
                           "S" to { a: Double, _: Double -> Math.sqrt(a) },"^" to { a: Double, b: Double -> Math.pow(a, b) })

    while(postfijo.size > 0){
        var token = postfijo.removeLast()
        if (token in operadores) {
            if(pila.size == 1 && token == "-"){
                pila.push(pila.pop() * -1)
                continue
            }
            val b = if(token == "S") 0.0 else pila.pop()
            val a = pila.pop()
            if (token == "/" && b == 0.0) throw ArithmeticException("División por cero")
            if (token == "S" && a < 0) throw ArithmeticException("Raíz cuadrada de un número negativo")
            pila.push(operadores[token]!!(a, b))
        } else pila.push(token.toDouble())
    }
    return pila.pop()
}

fun Expresion(expr : String) : ArrayDeque<String>{
    var expresion = expr + " "; var i = 0; var emp = ""
    var oper = ArrayDeque<String>()
    while(i < expresion.length){
        var character = expresion[i]
        if(character.isDigit() || character == '.') emp += character
        else{
            if(character == ' ' && emp != ""){
                oper.push(emp)
                emp = ""
            }
            else if(character != ' '){
                if(emp != ""){
                    oper.push(emp)
                    emp = ""
                }
                oper.push(character.toString())
            }
        }
        i++
    }
    return oper
}

fun Convertir(expr : ArrayDeque<String>) : ArrayDeque<String>{
    val jerarquia = mapOf("+" to 1, "-" to 1, "*" to 2, "/" to 2, "^" to 3, "M" to 2, "%" to 2, "S" to 3,"(" to 0,")" to 0)
    var operadores = ArrayDeque<String>(); var resultado = ArrayDeque<String>()
    while(!expr.isEmpty()){
        var oper = expr.removeLast()
        if(!jerarquia.contains(oper)) resultado.push(oper)
        else if(oper == "(") operadores.push(oper)
        else if(oper == ")"){
            while(operadores.size > 0 && operadores.peek() != "("){
                resultado.push(operadores.pop())
            }

            if(operadores.size > 0 && operadores.peek() != "(") throw IllegalArgumentException("Expresion invalida")
            else operadores.pop()
        }
        else{
            while(operadores.size > 0 && jerarquia.getValue(oper) <= jerarquia.getValue(operadores.peek())){
                resultado.push(operadores.pop())
            }
            operadores.push(oper)
        }
    }
    while(operadores.size > 0){
        resultado.push(operadores.pop())
    }
    return resultado
}