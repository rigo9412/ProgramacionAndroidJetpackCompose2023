fun main() {
    camelCase("Hola xd buenas")
}

fun camelCase(value: String? = "default"){
    if(value == null) return;

    var str = value.split(" ")
    var cad: String = ""
    var camelString: String = ""
    
    for (item in str) {
        if(item == str[0]) {
            cad = item[0].lowercase() + item.substring(1);
        }
        else {
            cad = item[0].uppercase() + item.substring(1);
        }
        camelString = camelString + cad
    }
    print(camelString)
}