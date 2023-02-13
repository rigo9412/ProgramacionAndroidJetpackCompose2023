fun main() {
    println(ruleN3(10_017_019_990_047_100,55))
}
 
fun ruleN3(value: Long, value1: Long): String {
    var pasos :Int = 1;
    var pares :Int = 0;
    var impares :Int = 1;
    var num = value
    while(num != 1L){
        if(num == value1) {return "SE ENCONTRO EN EL NUMERO " + num + " EN EL PASO: " + (pasos);}
        if(num % 2L == 0L) {num = num/2L; pares += 1;}
        else {
            num = (num * 3L) + 1L; 
            impares += 1
        }
        pasos += 1
    }
    return "PASO=" + pasos + " PARES=" + pares + " IMPARES=" + impares 
    
}