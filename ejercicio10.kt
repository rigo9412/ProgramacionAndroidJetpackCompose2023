// 10.Elaborar una función que reciba una operación aritmética en formato texto y que la resuelva,
// la operación solicitada son suma, resta, división, producto, residuo y porcentaje.
// a.Agregar funcionalidad de jerarquía de oper
// b.Agregar funcionalidad de raíz cuadrada

fun main(){
println(operacionAritmetica("S4"))
}

fun operacionAritmetica(op: String): Double {
    val nums = mutableListOf<Double>()
    val oper = mutableListOf<Char>()
    var i = 0

    while (i < op.length) {
        when {op[i].isDigit() -> {
            var numStr = ""
            while (i < op.length &&
                (op[i].isDigit() || op[i] == '.'))
            { numStr += op[i++] }
            nums.add(numStr.toDouble())
        }
            op[i] == '+' ||
            op[i] == '-' ||
            op[i] == 'R' ||
            op[i] == '%' ||
            op[i] == '*' ||
            op[i] == '/' ||
            op[i] == 'S' -> { oper.add(op[i++]) } op[i] == '(' -> { oper.add(op[i++])
        }
            op[i] == ')' -> {
                while (oper.last() != '(') {
                    if (oper.last() == 'S'){
                        val op = oper.removeLast()
                        val n1 = nums.removeLast()
                        nums.add(verificar(op, n1))
                    } else {
                        val op = oper.removeLast()
                        val n2 = nums.removeLast()
                        val n1 = nums.removeLast()
                        nums.add(verificar(op, n1, n2))
                    }
                }
                oper.removeLast();i++            }
            op[i] == ' ' -> { i++}
            else -> { throw IllegalArgumentException("Error") }
        }
    }
    while (oper.isNotEmpty()) {
        if (oper.last() == 'S'){
            val op = oper.removeLast()
            val n1 = nums.removeLast()
            nums.add(verificar(op, n1))
        } else{
            val op = oper.removeLast()
            val n2 = nums.removeLast()
            val n1 = nums.removeLast()
            nums.add(verificar(op, n1, n2))
        }
    }
    return nums[0]
}
fun verificar(op: Char, n1: Double, n2: Double = 0.0): Double {
    return when (op) {
        '+' -> n1 + n2
        '*' -> n1 * n2
        '/' -> n1 / n2
        '-' -> n1 - n2
        'R' -> n1 % n2
        'S' -> kotlin.math.sqrt(n1)
        '%' -> n1 * (n2/100)
        else -> throw IllegalArgumentException("Error")
    }
}