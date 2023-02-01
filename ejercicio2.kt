fun main() {
    cambioCase("Luis otoniel soto maldonado")
}

fun cambioCase(value: String? = "default"){
    if(value == null) return;

    var string = value.split(" ")
    var pal: String = ""
    var cadenaCamel: String = ""
    
    for (item in string) {
        if(item == string[0]) {
            pal = item[0].lowercase() + item.substring(1);
        }
        else {
            pal = item[0].uppercase() + item.substring(1);
        }
        cadenaCamel = cadenaCamel + pal
    }
    print(cadenaCamel)
}
