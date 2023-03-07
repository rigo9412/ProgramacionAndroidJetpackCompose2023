//Elaborar una función que reciba 3 números enteros y que determine si el último
//dígito del número se repite entre alguno de los 3 números.

fun main() {
    LastDigit(45,3329,21)
}


fun LastDigit(num1:Int,num2: Int,num3: Int):Boolean{
    var aux1: Int = if(num1.toString().length==1) {
        0
    } else {
        Math.max(0,num1.toString().length-1)
    }
    var aux2: Int = if(num2.toString().length==1){
        0
    } else{
        Math.max(0,num2.toString().length-1)
    }
    var aux3 = if(num3.toString().length==1){
        0
    } else{
        Math.max(0,num3.toString().length-1)
    }

    var new1 : Int = num1.toString().subSequence(0,aux1).toString().toInt()
    var new2 : Int = num2.toString().subSequence(0,aux2).toString().toInt()
    var new3 : Int = num3.toString().subSequence(0,aux3).toString().toInt()
    val newNumbersArray  = intArrayOf(new1,new2,new3)
    val LastDigitsArray = intArrayOf(num1%10,num2%10,num3%10)
    val found = newNumbersArray.any(LastDigitsArray::contains)

    return found
}