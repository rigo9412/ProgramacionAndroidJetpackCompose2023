fun main() {
    ruleN3(10,8)
}
 
fun ruleN3(value: Int, value1: Int) {
    var pasos :Int = 0;
    var pares :Int = 0;
    var impares :Int = 1;
    var num = value
    while(num != 1){
        println(num)  
        pasos += 1
        if(num % 2 == 0) {num = num/2; pares += 1;}
        else {
            num = (num * 3) + 1; 
            impares += 1
        }
        if(num == value1) {println("Se encontro el numero " + value1 + " en el paso " + pasos); return;}
      
    }
    println("Numeros pares: " + pares)
    println("Numeros impares: " + impares)
    println("Numeros pasos: " + pasos+1)
}