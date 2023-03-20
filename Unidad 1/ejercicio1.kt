fun main(){
    stringInvert("Hello, World!!!")
    stringInvert(null)
    stringInvert()
}
fun stringInvert(value : String? = "default") {
    if(value == null) return;
    for(item in value.lastIndex downTo 0){
        print(value[item])
    }
}