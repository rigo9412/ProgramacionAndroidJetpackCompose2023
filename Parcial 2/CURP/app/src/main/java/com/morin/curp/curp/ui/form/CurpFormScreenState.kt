package com.morin.curp.curp.ui.form

sealed class FormCurpScreenState {
    object Init : FormCurpScreenState()
    class Loading(val message: String) : FormCurpScreenState()
    object Loaded : FormCurpScreenState()
    class Done(val curp: String, val name: String) : FormCurpScreenState()
    class Error(val message: String) : FormCurpScreenState()
}
