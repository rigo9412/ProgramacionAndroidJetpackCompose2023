import java.util.*
fun main(){
    print(ultimonumero(1,4,4))
}

fun ultimonumero(val1: Int, val2: Int, val3: Int ): Boolean{
    var cif1 = (val1 % 10).toString()
    var cif2 = (val2 % 10).toString()
    var cif3 = (val3 % 10).toString()
    var cifcom = (val1/10).toString() + (val2/10).toString() + (val3/10).toString()
    var res =(cifcom.contains(cif1) || cifcom.contains(cif2) || cifcom.contains(cif3)) || (val1 == val2 || val1 == val3 || val2 == val3)
    return res
}