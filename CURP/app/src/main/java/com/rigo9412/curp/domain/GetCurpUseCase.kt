package com.rigo9412.curp.domain

import com.rigo9412.curp.ui.form.ui.CurpFormModelState
import com.rigo9412.curp.utils.*
import java.text.Normalizer
import java.time.LocalDate


class GetCurpUseCase {

    fun invoque(data: CurpFormModelState): ResultCase {
        try{
            var curp = ""
            val inputData = data;
            val date = LocalDate.parse(inputData.birth, FORMATTER_INPUT)
            val name = clean(inputData.name)
            val middleName = clean(inputData.middleName)
            val lastname = clean(inputData.lastName)

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


            return  ResultCase.ResultSuccess("$curp${generateValidatorDigit(curp)}")




        }
        catch(e: java.lang.Exception) {

            return ResultCase.ResultError(  code = 500, error = "Ocurrio un problema al genera el CURP")

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


}