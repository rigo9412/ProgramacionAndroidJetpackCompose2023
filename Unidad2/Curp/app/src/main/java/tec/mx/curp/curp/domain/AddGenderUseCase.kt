package tec.mx.curp.curp.domain

class AddGenderUseCase {

    fun invoque(string: String) : ResultCase {

        if(string.isEmpty()){
            return ResultCase.ResultError(code = 1, error = "No se selecciono el genero")
        }

        return ResultCase.ResultValid;
    }
}