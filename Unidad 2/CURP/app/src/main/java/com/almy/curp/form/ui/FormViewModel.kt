package com.almy.curp.form.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.almy.curp.form.domain.estados
import com.almy.curp.form.domain.generos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timerTask

class FormViewModel: ViewModel() {
    val PATTERN_NAME = Regex("[a-zA-zñÑáéíóúÁÉÍÓÚÜ'° .,\\\\s]*")

    @RequiresApi(Build.VERSION_CODES.O)
    private val FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    @RequiresApi(Build.VERSION_CODES.O)
    private val FORMATTER_CURP = DateTimeFormatter.ofPattern("yyMMdd")

    val vowels = "AEIOUÁÉÍÓÚ"
    val consonants = "QWRTYPSDFGHJKLÑZXCVBNM"

    private val _uiStateData = MutableStateFlow<CurpUiModel>(CurpUiModel())
    val uiStateData: StateFlow<CurpUiModel> = _uiStateData

    private val _uiState = MutableStateFlow<FormUiState>( FormUiState.Init)
    val uiState: StateFlow<FormUiState> = _uiState


    init {
        initState()
    }

     fun initState() {
        _uiState.value = FormUiState.Loading("Cargando aplicación... espere un momento :)!!! ") //Iniciamos la pantalla de carga!

        //Iniciamos con valores por default
        _uiStateData.value = CurpUiModel(
            isValid = true,
            name= "ALDO EZEQUIEL",
            middleName= "RODRIGUEZ",
            lastName = "MENDEZ",
            birth = "2001-03-15",
            state = Pair("TS","Tamaulipas"),
            gender = Pair("H","Hombre"),
            sexList = getGeneros(),
            statesList = getEstados(),
        )

         Timer().schedule(timerTask {
             _uiState.value = FormUiState.Loaded

         }, 3000)
    }

    fun onChangeName(name: String){
        if (name.matches(PATTERN_NAME)){
            _uiStateData.value = _uiStateData.value.copy(name = name.uppercase())
        }
    }

    fun onChangeMiddleName(middleName: String){
        if (middleName.matches(PATTERN_NAME)){
            _uiStateData.value = _uiStateData.value.copy(middleName = middleName.uppercase())
        }
    }

    fun onChangeLastName(lastName: String){
        if (lastName.matches(PATTERN_NAME)){
            _uiStateData.value = _uiStateData.value.copy(lastName = lastName.uppercase())
        }
    }

    fun onChangeBirth(birth: String){
        _uiStateData.value = _uiStateData.value.copy(birth = birth)
    }

    fun onChangeState(state: Pair<String,String>){
        _uiStateData.value = _uiStateData.value.copy(state = state)
    }

    fun onChangeGender(gender: Pair<String,String>){
        _uiStateData.value = _uiStateData.value.copy(gender = gender)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generarCURP(){
        try {
            _uiState.value = FormUiState.Loading("Espero un momento... estamos generando su CURP")

            var name = uiStateData.value.name
            var middlename = uiStateData.value.middleName
            var lastName = uiStateData.value.lastName
            var birth = LocalDate.parse(uiStateData.value.birth, FORMATTER_INPUT)
            var gender = uiStateData.value.gender
            var state = uiStateData.value.state
            var curp = ""

            val formato = SimpleDateFormat("yyMMdd")

            //Obtener la primera inicial y la primer vocal del primer apellido
            curp += middlename[0]
            middlename = middlename.substring(1)

            for (c in middlename){
                if (vowels.contains(c))
                {
                    curp+=c
                    middlename = middlename.substring(1)
                    break
                }
            }

            //Obtener la primera inicial del segundo apellido y la primera inicial del nombre
            curp += if(lastName.isEmpty()) "X" else lastName[0]
            lastName = lastName.substring(1)
            curp+= name[0]
            name = name.substring(1)

            //Fecha de nacimiento en formato (AA/MM/DD)
            curp += birth.format(FORMATTER_CURP)

            //Agregar el genero
            curp += gender.first

            //Entidad Federativa (2 caracteres)
            curp+= state.first

            //Primeras consonantes del apellido
            for (c in middlename){
                if (consonants.contains(c))
                {
                    curp+=c
                    break
                }
            }

            if (lastName.isEmpty()){
                curp += "X"
            }else{
                for (c in lastName){
                    if (consonants.contains(c))
                    {
                        curp+=c
                        break
                    }
                }
            }

            for (c in name){
                if (consonants.contains(c))
                {
                    curp+=c
                    break
                }
            }

            //Diferenciador de homonimia y siglo (0 / A)
            curp += if(birth.year < 2000) "0" else "A"

            //Digito verificador
            curp += (generateValidatorDigit(curp)).toString()

            _uiStateData.value = _uiStateData.value.copy(curp = curp)

            Timer().schedule(timerTask {
                _uiState.value = FormUiState.Done(_uiStateData.value.curp,_uiStateData.value.name)
            }, 2000)

        }
        catch (e: java.lang.Exception){
            _uiState.value = FormUiState.Error("Ha ocurrido un error...")
        }

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

    fun getEstados():ArrayList<Pair<String,String>>{
        return estados
    }
    fun getGeneros() : ArrayList<Pair<String,String>>{
        return generos;
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
    val name: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val birth: String = "",
    val gender: Pair<String,String> = Pair<String,String>("",""),
    val state: Pair<String,String> = Pair<String,String>("",""),
    val sexList: ArrayList<Pair<String,String>> = ArrayList(),
    val statesList: ArrayList<Pair<String,String>> = ArrayList()
)