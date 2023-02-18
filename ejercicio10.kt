import java.util.*
import java.util.regex.Pattern
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val expression = "1+1+sqrt(16)+(10M3)+(5/2)+4.6+.1"
    println(evalExpression(expression))
}
// Usar esta directamente
fun evalExpression(expression: String): Double = evalPostfix(toPostfix(expression))
fun toPostfix(expression: String): String {
    val regex = Pattern.compile("\\d+(\\.\\d+)?|\\+|-|\\*|/|%|\\^|\\(|\\)|sqrt|M|P")
    val matches = regex.matcher(expression)

    val operators = Stack<String>()
    val postfix = LinkedList<String>()

    while (matches.find()) {
        val token = matches.group()
        if (isNumber(token)) {
            postfix.add(token)
        } else if (isOperator(token)) {
            while (operators.isNotEmpty() && isOperator(operators.peek()) && getPrecedence(token) <= getPrecedence(operators.peek())) {
                postfix.add(operators.pop())
            }
            operators.push(token)
        } else if (token == "(") {
            operators.push(token)
        } else if (token == ")") {
            while (operators.isNotEmpty() && operators.peek() != "(") {
                postfix.add(operators.pop())
            }
            operators.pop()
        } else if (token == "sqrt") {
            operators.push(token)
        } else {
            throw IllegalArgumentException("Token desconocido en postfijo: $token")
        }
    }
    while (operators.isNotEmpty()) {
        postfix.add(operators.pop())
    }
    return postfix.joinToString(" ") // Aqui se separan de nuevo saludos
}

fun evalPostfix(postfix: String): Double {
    val tokens = postfix.split(" ")
    val opStack = Stack<Double>()

    for (token in tokens) {
        if (isNumber(token)) {
            opStack.push(token.toDouble())
        } else if (isOperator(token)) {
            if (token == "sqrt") {
                val operand = opStack.pop()
                val result = sqrt(operand)
                opStack.push(result)
            } else {
                val b = opStack.pop()
                val a = opStack.pop()
                val result = eval(a, b, token)
                opStack.push(result)
            }
        } else {
            throw IllegalArgumentException("Token desconocido en evaluacion postfija: $token")
        }
    }

    return opStack.pop()
}
fun isNumber(token: String): Boolean = token.toDoubleOrNull() != null
fun isOperator(token: String): Boolean = token in setOf("+", "-", "*", "/", "%", "^", "sqrt", "M", "P")
fun getPrecedence(op: String): Int {
    return when (op) {
        "+", "-" -> 1
        "*", "/", "M", "%", "P" -> 2
        "^", "sqrt" -> 3
        else -> throw IllegalArgumentException("Operador no valido: $op")
    }
}
fun eval(opA: Double, opB: Double, op: String): Double {
    return when (op) {
        "+" -> opA + opB
        "-" -> opA - opB
        "*" -> opA * opB
        "/" -> opA / opB
        "%", "P" -> opA / opB * 100
        "^" -> opA.pow(opB)
        "sqrt" -> sqrt(opB)
        "M" -> opA % opB
        else -> throw IllegalArgumentException("Operador no valido: $op")
    }
}
