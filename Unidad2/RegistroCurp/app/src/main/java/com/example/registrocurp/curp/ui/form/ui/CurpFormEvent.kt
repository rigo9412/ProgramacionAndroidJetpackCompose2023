package com.example.registrocurp.curp.ui.form.ui

sealed class CurpFormEvent {
    data class NameChanged(val name:String) : CurpFormEvent()
    data class MiddleNameChanged(val middleName:String) : CurpFormEvent()
    data class LastNameChanged(val lastName:String) : CurpFormEvent()
    data class BirthChanged(val birth:String) : CurpFormEvent()
    data class GenderChanged(val gender:Pair<String,String>) : CurpFormEvent()
    data class StateChanged(val state:Pair<String,String>) : CurpFormEvent()
    object Submit : CurpFormEvent()

}