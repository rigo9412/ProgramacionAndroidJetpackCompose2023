package com.practica.curpmovil.form.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.practica.curpmovil.form.domain.*
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import kotlin.random.Random


class FormViewModel : ViewModel() {
    private var _uiStateData = MutableStateFlow<CurpUIModel>(CurpUIModel())
    public var uiStateData: StateFlow<CurpUIModel> = _uiStateData

    private var _uiState = MutableStateFlow<FormUiState>(FormUiState.Empty)
    var uiState: StateFlow<FormUiState> = _uiState


    init {
        initState()
    }

    private fun initState() {
        _uiStateData.value = CurpUIModel(
            sexList = getGenders(),
            statesList = getStates(),
        )
        _uiState.value = FormUiState.MainMenu
    }

    fun onChangeName(name: String) {
        if (name.matches(patternName)) {
            _uiStateData.value = _uiStateData.value.copy(name = name.uppercase())
        }
    }

    fun onChangeMiddleName(middleName : String){
        if (middleName.matches(patternName)){
            _uiStateData.value = _uiStateData.value.copy(middleName = middleName.uppercase())
        }
    }

    fun onChangeLastName(lastName : String){
        if (lastName.matches(patternName)){
            _uiStateData.value = _uiStateData.value.copy(lastName = lastName.uppercase())
        }
    }

    fun onChangeGender(gender:Pair<String, String>){
        _uiStateData.value = _uiStateData.value.copy(gender = gender)
    }

    fun onChangeBirth(birth:String){
        _uiStateData.value = _uiStateData.value.copy(birth = birth)
    }
    fun onChangeState(state: Pair<String, String>) {
        _uiStateData.value = _uiStateData.value.copy(state = state)
    }

    private fun getGenders(): ArrayList<Pair<String, String>> {
        return sexos
    }

    private fun getStates(): ArrayList<Pair<String, String>> {
        return estados
    }
//    private fun generateCURP():String{
//
//    }

    val patternName = Regex("[a-zA-z\\s]*")

    sealed class FormUiState {
        object Empty : FormUiState()
        object Loading : FormUiState()
        object Loaded : FormUiState()
        object Success : FormUiState()
        //agregar mas objects : FormUiSstate, que serian WizardName, WizardGender, WizardBirth, WizardState
        object WizardName : FormUiState()
        object WizardGender : FormUiState()
        object WizardBirth : FormUiState()
        object WizardState : FormUiState()
        object MainMenu : FormUiState()
        object FormComplete : FormUiState()
        class Error(val message: String) : FormUiState()
    }
    public fun getCurp():String{
        return uiStateData.value.curp
    }

    fun generateValidatorDigit(curp:String):Int{
        var countCurp = 18
        var sum = 0
        val chars = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
        for(caracter in curp){
            val index = chars.indexOf(caracter)
            if(index > -1){
                val result = index * countCurp
                countCurp--
                sum += result
            }
            else{

            }
        }

        var numVer = sum % 10
        numVer = 10 - numVer
        numVer = if (numVer == 10) 0 else numVer

        return numVer
    }

    @RequiresApi(Build.VERSION_CODES.O)
     fun generateCurpToShow() : String{
        _uiState.value = FormUiState.Loading

        var curp = ""
        val inputData = _uiStateData.value;
        val date = LocalDate.parse(inputData.birth,FORMATTER_INPUT)
        val name = clean(inputData.name)
        val middleName = clean(inputData.middleName)
        val lastName = clean(inputData.lastName)

        //curp = generarCURP(inputData.name,inputData.middleName,inputData.lastName,inputData.gender.first[0],inputData.state.first,inputData.birth)
        curp += middleName[0]
        curp += getLetterforPosition(middleName,1,VOCAL)
        curp += if(lastName.isEmpty()) 'X' else lastName[0]
        curp += name[0]
        curp += date.format(FORMATTER_CURP)
        curp += inputData.gender.first
        curp += inputData.state.first
        //curp += getLetterforPosition(middleName.substring(1),1)
        curp + inputData.lastName.slice(1..2).replace(regex = Regex("[A|E|I|O|U]"), replacement = "")
        curp += inputData.middleName.slice(1..3).replace(regex = Regex("[A|E|I|O|U]"), replacement = "")[0]
        curp += inputData.name.slice(1..3).replace(regex = Regex("[A|E|I|O|U]"), replacement = "")
        curp += if (inputData.birth.substring(0, 4).toInt() < 2000) (0..9).random() else ('A'..'Z').random().toString()
        curp += (0..9).random()
        uiStateData.value.curp = curp
        _uiState.value = FormUiState.Success
        return  curp
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun regresarPaginaPrincipal(){
        _uiState.value = FormUiState.Loading

        _uiState.value = FormUiState.MainMenu




    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun toGenderWizard(){
        _uiState.value = FormUiState.Loading

        _uiState.value = FormUiState.WizardGender
    }

    //Make a function to go to to toNameWizard, like the function toGenderWizard
    @RequiresApi(Build.VERSION_CODES.O)
    fun toNameWizard(){
        _uiState.value = FormUiState.Loading

        _uiState.value = FormUiState.WizardName
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun generarCURP(nombre: String, apellidoPaterno: String, apellidoMaterno: String, sexo: Char, estado: String, fechaNacimiento: String): String {
        val primerLetraApellidoPaterno = apellidoPaterno[0]
        val primeraVocalApellidoPaterno = buscarPrimeraVocal(apellidoPaterno)
        val primerLetraApellidoMaterno = apellidoMaterno[0]
        val primerLetraNombre = nombre[0]
        val fechaNacimientoFormatted = fechaNacimiento
        val digitoVerificador = Random.nextInt(0, 10)

        return "${primerLetraApellidoPaterno}${primeraVocalApellidoPaterno}${primerLetraApellidoMaterno}${primerLetraNombre}${fechaNacimientoFormatted}${sexo}${estado}${digitoVerificador}"
    }







    fun buscarPrimeraVocal(cadena: String): Char {
        val vocales = listOf('a', 'e', 'i', 'o', 'u')
        for (c in cadena) {
            if (vocales.contains(c.toLowerCase())) {
                return c.toUpperCase()
            }
        }
        return 'X' // Si no hay vocales, se pone X
    }

    fun toFormCompleta() {
        _uiState.value = FormUiState.Loading

        _uiState.value = FormUiState.FormComplete
    }

    fun toStateWizard() {
        _uiState.value = FormUiState.Loading

        _uiState.value = FormUiState.WizardState
    }

    fun toDateBirthPage() {
        _uiState.value = FormUiState.Loading

        _uiState.value = FormUiState.WizardBirth
    }


}




