package com.rigo9412.curp.ui.form.ui

sealed class FormCurpScreenState {
    object Init : FormCurpScreenState()
    class Loading(val message: String) : FormCurpScreenState()
    object Loaded : FormCurpScreenState()
    class Done(val curp: String, val name: String) : FormCurpScreenState()
    class Error(val message: String) : FormCurpScreenState()
}
