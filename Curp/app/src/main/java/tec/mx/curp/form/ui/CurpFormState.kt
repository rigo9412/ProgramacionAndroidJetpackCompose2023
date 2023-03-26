package tec.mx.curp.form.ui

data class CurpFormModelState(
    val name: String = "",
    val nameError: String? = null,
    val middleName: String = "",
    val middleNameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val birth: String = "",
    val birthError: String? = null,
    val gender: Pair<String,String> = Pair<String,String>("",""),
    var genderError: String? = null,
    val state: Pair<String,String> = Pair<String,String>("",""),
    val stateError: String? = null,
    val sexList: ArrayList<Pair<String,String>>  = ArrayList(),
    val statesList: ArrayList<Pair<String,String>> = ArrayList(),
)