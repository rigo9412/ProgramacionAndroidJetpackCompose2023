package tec.mx.curp.curp.domain

class AddStateUseCase {
    fun invoque(string: String) : ResultCase {

        if(string.isEmpty()){
            return ResultCase.ResultError(code = 1, error = "No se selecciono el estado")
        }

        return ResultCase.ResultValid;
    }
}