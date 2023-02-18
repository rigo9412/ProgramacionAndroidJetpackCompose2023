//Elaborar una función que reciba una operación aritmética en formato texto y que la
//resuelva, la operación solicitada son suma, resta, división, producto, residuo y porcentaje.
//a. Agregar funcionalidad de jerarquía de operadores
//b. Agregar funcionalidad de raíz cuadrada.
import java.util.Stack
import kotlin.math.*

fun main(){
    println(Calculadora("( 3 + 2 ) / 2 + 5 * 10 R 2"))
}

fun Calculadora(operation: String):Double{
    val elements = operation.split(" ")
    val numStack = Stack<Double>()
    val opStack = Stack<String>()

    var i = 0
    while (i<elements.size){
        if(elements[i]=="("){
            opStack.push(elements[i])
        }else if(elements[i]==")"){
            while (opStack.peek()!="("){
                val operator = opStack.pop()
                val secondNumb = numStack.pop()
                val firstNumb = numStack.pop()
                val res= DoOperation(firstNumb, secondNumb, operator)
               numStack.push(res)
            }
            opStack.pop()
        }else if(isOperator(elements[i])){
            while(!opStack.empty() && Priority(elements[i],opStack.peek())){
                val operator = opStack.pop()
                val secondNumb = numStack.pop()
                val firstNumb = numStack.pop()
                val res= DoOperation(firstNumb, secondNumb, operator)
                numStack.push(res)
            }
            opStack.push(elements[i])
        }else{
            numStack.push(elements[i].toDouble())
        }
        i++
    }
    while (!opStack.empty()){
        val operator = opStack.pop()
        val secondNumb = numStack.pop()
        val firstNumb = numStack.pop()
        val res= DoOperation(firstNumb, secondNumb, operator)
        numStack.push(res)
    }
    return numStack.pop()
}

fun isOperator(elemento: String): Boolean {
    return elemento == "+" || elemento == "-" || elemento == "*" || elemento == "/" || elemento == "%" || elemento == "R" || elemento == "!"
}

fun Priority(op1: String, op2: String): Boolean {
    if (op2 == "(" || op2 == ")") {
        return false
    }
    if ((op1 == "*" || op1 == "/" || op1 == "%" || op1 == "R" || op1 == "!" ) && (op2 == "+" || op2 == "-")) {
        return false
    }
    return true
}

fun DoOperation(first: Double, second: Double, operator: String): Double {
    when (operator) {
        "+" -> return first + second
        "-" -> return first - second
        "*" -> return first * second
        "/" -> return first / second
        "R" -> return first % second
        "%" -> return first * second / 100
        "!" -> return Math.sqrt(second)
    }
    return 0.0
}