package com.example.generadorcurp.form.domain.ui

sealed class CurpFormScreenState {
    object Init: CurpFormScreenState()
    class Loading(val message : String) : CurpFormScreenState()
    object Loaded : CurpFormScreenState()
    class Done(val curp: String, val name : String) : CurpFormScreenState()
    class Error(val message: String) : CurpFormScreenState()
}