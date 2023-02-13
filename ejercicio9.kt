fun main() {
    ruleN3(10_017_019_990_047_100,11272)
}
 
fun ruleN3(value: Long, value1: Long) {
    var pasos :Int = 1;
    var pares :Int = 0;
    var impares :Int = 1;
    var num = value
    while(num != 1L){
        println(num)  
        if(num % 2L == 0L) {num = num/2L; pares += 1;}
        else {
            num = (num * 3L) + 1L; 
            impares += 1
        }
        if(num == value1) {println("Se encontro el numero " + value1 + " en el paso " + (pasos + 1)); return;}
        pasos += 1
    }
    println("Numeros pares: " + pares)
    println("Numeros impares: " + impares)
    println("Numeros pasos: " + pasos)
}