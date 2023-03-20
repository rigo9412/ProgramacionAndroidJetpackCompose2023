package com.example.registro.form.ui

import android.os.Build
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.registro.form.domains.*
import com.example.registro.form.ui.FormUiState.Loaded
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.Normalizer
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask

//private val savedStateHandle: SavedStateHandle
class FormViewModel(): ViewModel() {

    private val _uiStateData = MutableStateFlow<CurpUiModel>(CurpUiModel())
    val uiStateData: StateFlow<CurpUiModel> = _uiStateData

    private val _uiState = MutableStateFlow<FormUiState>( FormUiState.Init) //savedStateHandle["formState"] ?:
    val uiState: StateFlow<FormUiState> = _uiState

    init {
        initState()
    }

    fun initState() {

        _uiState.value = FormUiState.Loading("cargando...estamos trabajando...")
        _uiStateData.value = CurpUiModel(
            isValid = true,
            name = "RIGOBERTO",
            middleName = "RAMOS",
            lastname = "APARICIO",
            birth = "1994-11-12",
            state = Pair("TS", "Tamaulipas"),
            gender = Pair("H", "Hombre"),
            sexList = getGenders(),
            statesList = getStates(),
        )

        Timer().schedule(timerTask {
            _uiState.value = FormUiState.Loaded

        }, 3000)
    }

    fun onChangeName(name: String) {
        if (name.matches(PATTERN_NAME)) {
            //_uiStateData.value.name = "sdsdf"
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateCURP() {

        try{
            _uiState.value = FormUiState.Loading("Pi Po Pi Pu...Calculando CURP")
            var curp = ""
            val inputData = _uiStateData.value;
            val date = LocalDate.parse(inputData.birth, FORMATTER_INPUT)
            val name = clean(inputData.name)
            val middleName = clean(inputData.middleName)
            val lastname = clean(inputData.lastname)

            curp += middleName[0]
            curp += getLetterForPostion(middleName, 1, VOCAL)
            curp += if (lastname.isEmpty()) 'X' else lastname[0]
            curp += name[0]
            curp += date.format(FORMATTER_CURP)
            curp += inputData.gender.first
            curp += inputData.state.first
            curp += getLetterForPostion(middleName.substring(1), 1, CONSONANTS)
            curp += if (lastname.isEmpty()) 'X' else getLetterForPostion(
                lastname.substring(1),
                1,
                CONSONANTS
            )
            curp += getLetterForPostion(name.substring(1), 1, CONSONANTS)


            if (BLACK_LIST.contains(curp.substring(0, 4))) {
                curp = curp[0] + "X" + curp.substring(2);
            }

            curp += if (date.year < 2000) "0" else "A"

            _uiStateData.value = _uiStateData.value.copy(curp = "$curp${generateValidatorDigit(curp)}")

            Timer().schedule(timerTask {
                _uiState.value = FormUiState.Done(_uiStateData.value.curp,_uiStateData.value.name)
            }, 2000)


        }
        catch(e: java.lang.Exception) {
            _uiState.value = FormUiState.Error("ERROR ROMPISTE LA APP...TE VOY A ENCONTRAR >:(")

        }

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
                //TODO: ERROR FALTA
            }

        }
        var numVer = sum % 10;
        numVer = 10 - numVer;
        numVer = if(numVer == 10) 0 else numVer;

        return numVer;
    }

    private fun clean(value: String) : String {
        var valueCleaned = value

        valueCleaned.replace("Ñ","X")
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
    object Init : FormUiState()
    class Loading(val message: String) : FormUiState()

    object Loaded : FormUiState()
    class Done(val curp: String, val name: String) : FormUiState()
    class Error(val message: String) : FormUiState()
}

data class CurpUiModel(
    val isValid: Boolean = false,
    val curp: String = "",
    var name: String = "",
    val middleName: String = "",
    val lastname: String = "",
    val birth: String = "",
    val gender: Pair<String,String> = Pair<String,String>("",""),
    val state: Pair<String,String> = Pair<String,String>("",""),
    val sexList: ArrayList<Pair<String,String>>  = ArrayList(),
    val statesList: ArrayList<Pair<String,String>> = ArrayList(),
)