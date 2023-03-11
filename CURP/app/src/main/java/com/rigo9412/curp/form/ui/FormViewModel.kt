package com.rigo9412.curp.form.ui

import androidx.lifecycle.ViewModel
import com.rigo9412.curp.form.domain.blackList
import com.rigo9412.curp.form.domain.estados
import com.rigo9412.curp.form.domain.generos
import com.rigo9412.curp.form.ui.FormUiState.Loaded
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.Normalizer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FormViewModel: ViewModel() {
    val PATTERN_NAME = Regex("[a-zA-zñÑáéíóúÁÉÍÓÚÜ'° .,\\\\s]*")

    private val COMPOSTION_NAME =  arrayListOf("MARIA", "MA.", "MA", "JOSE", "J", "J." )
    private val PREPOSTION_CONJUNTION_CONTRADICTION = arrayListOf<String>("DA", "DAS", "DE", "DEL", "DER", "DI", "DIE", "DD", "EL", "LA", "LOS", "LAS", "LE", "LES", "MAC", "MC", "VAN", "VON", "Y", "J", "MA" );
    private val VOCAL = "AEIOU";
    private val CONSONANTS = " BCDFGHJKLMNÑPQRSTVXZWY";
    private val FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val FORMATTER_CURP = DateTimeFormatter.ofPattern("yyMMdd")
    private val _uiStateData = MutableStateFlow<CurpUiModel>(CurpUiModel())
    val uiStateData: StateFlow<CurpUiModel> = _uiStateData

    private val _uiState = MutableStateFlow<FormUiState>(FormUiState.Empty)
    val uiState: StateFlow<FormUiState> = _uiState

    init {
        initState()
    }


    private fun initState() {
        //_uiState.value = FormUiState.Loading
        _uiStateData.value = CurpUiModel(
            isValid = true,
            name= "RIGOBERTO",
            middleName= "RAMOS",
            lastname = "APARICIO",
            birth = "1994-11-12",
            state = Pair("TS","Tamaulipas"),
            gender = Pair("H","Hombre"),
            sexList = getGenders(),
            statesList = getStates(),
        )
        _uiState.value = Loaded
    }


    fun onChangeName(name: String) {
       if (name.matches(PATTERN_NAME)) {
            _uiStateData.value = _uiStateData.value.copy(name = name.uppercase())
       }
        validateInputs()
    }

    fun onChangeMiddlename(middleName: String){
        if (middleName.matches(PATTERN_NAME)) {
            _uiStateData.value = _uiStateData.value.copy(middleName = middleName.uppercase())

        }
        validateInputs()
    }

    fun onChangeLastname(lastname: String){
        if (lastname.matches(PATTERN_NAME)) {
            _uiStateData.value = _uiStateData.value.copy(lastname = lastname.uppercase())

        }
        validateInputs()

    }

    fun onChangeGender(gender: Pair<String,String>){
        _uiStateData.value = _uiStateData.value.copy(gender = gender)
        validateInputs()
    }

    fun onChangeBirth(string: String){
        _uiStateData.value = _uiStateData.value.copy(birth = string)
        validateInputs()
    }

    fun onChangeState(state: Pair<String,String>){
        _uiStateData.value = _uiStateData.value.copy(state = state)
        validateInputs()
    }

    fun generateCURP(){
        var curp = ""
        val inputData = _uiStateData.value;
        val date = LocalDate.parse(inputData.birth, FORMATTER_INPUT)
        val name = Clean(inputData.name)
        val middleName = Clean(inputData.middleName)
        val lastname = Clean(inputData.lastname)

        curp += middleName[0]
        curp += getLetterForPostion(middleName,1, VOCAL)
        curp += if(lastname.isEmpty()) 'X' else lastname[0]
        curp += name[0]
        curp += date.format(FORMATTER_CURP)
        curp += inputData.gender.first
        curp += inputData.state.first
        curp += getLetterForPostion(middleName.substring(1),1, CONSONANTS)
        curp += if(lastname.isEmpty()) 'X' else  getLetterForPostion(lastname.substring(1),1, CONSONANTS)
        curp += getLetterForPostion(name.substring(1),1, CONSONANTS)


        if (blackList.contains(curp.substring(0, 4)))
        {
            curp = curp[0] + "X" + curp.substring(2);
        }

        curp += if(date.year < 2000) "0" else "A"

        _uiStateData.value = _uiStateData.value.copy(curp = "$curp${generateValidatorDigit(curp)}")
    }


    private fun getLetterForPostion(value: String,postion: Int, listLetters: String): Char{
        var indexPosition = 1;
        for (i in value){
            if(listLetters.contains(i)){
                if(indexPosition == postion){
                    return if (i == 'Ñ') 'X' else i
                }
                indexPosition++
            }
        }
        return 'X'
    }



    private fun  generateValidatorDigit(curp: String): Int{
        var countCurp = 18;
        var sum = 0;
        val chars = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        for(caracter in curp)
        {
            val index = chars.indexOf(caracter);

            if (index > -1)
            {
                val result = index * countCurp;
                countCurp--;
                sum += result;
            }else{
                //TODO ERROR FALTA
            }

        }
        var numVer = sum % 10;
        numVer = 10 - numVer;
        numVer = if(numVer == 10) 0 else numVer;

        return numVer;
    }

    private fun Clean(value: String) : String {
        var valueCleaned = value

        valueCleaned = Normalizer.normalize(valueCleaned, Normalizer.Form.NFD);
        valueCleaned = valueCleaned.replace("[\\p{InCombiningDiacriticalMarks}]", "");

        // Criterios de excepcion
        var words = valueCleaned.split(' ')
            .filter { !it.isNullOrEmpty() }
            .toMutableList();

        // Preposición, conjunción, contraccion
        words = words.filter { !PREPOSTION_CONJUNTION_CONTRADICTION.contains(it)}
            .toMutableList();

        if (words.count() >= 2 && COMPOSTION_NAME.contains(words[0]))
        {
            words.removeAt(0)
        }

        // Caracteres especiales
        valueCleaned = words[0]
            .replace('/', 'X')
            .replace('-', 'X')
            .replace('.', 'X');


        return valueCleaned
    }

    private fun validateInputs()  {
        var isValid = true
        if(_uiStateData.value.name.isEmpty()){
            isValid =  false
        }
        else if(_uiStateData.value.middleName.isEmpty()) {
            isValid =  false
        }
//        else if(_uiStateData.value.lastname.isEmpty()) {
//            return false
//        }
        else if(_uiStateData.value.birth.isEmpty()) {
            isValid =  false
        }
        else if(_uiStateData.value.gender.first.isEmpty()) {
            isValid =  false
        }
        else if(_uiStateData.value.state.first.isEmpty()) {
            isValid =  false
        }


        _uiStateData.value = _uiStateData.value.copy( isValid = isValid)

    }




    private fun getGenders() : ArrayList<Pair<String,String>>{
        //todo simular un delay
        return generos
    }

    private fun getStates() : ArrayList<Pair<String,String>>{
        //todo simular un delay
        return estados
    }
}



sealed class FormUiState {
    object Empty : FormUiState()
    object Loading : FormUiState()
    object Loaded : FormUiState()
    class Error(val message: String) : FormUiState()
}

data class CurpUiModel(
    val isValid: Boolean = false,
    val curp: String = "",
    val name: String = "",
    val middleName: String = "",
    val lastname: String = "",
    val birth: String = "",
    val gender: Pair<String,String> = Pair<String,String>("",""),
    val state: Pair<String,String> = Pair<String,String>("",""),
    val sexList: ArrayList<Pair<String,String>>  = ArrayList(),
    val statesList: ArrayList<Pair<String,String>> = ArrayList(),
)
