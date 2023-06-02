package com.almy.mochiapp.screens.LoginScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almy.mochiapp.firebase.*
import com.almy.mochiapp.models.Assigment
import com.almy.mochiapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.concurrent.timerTask

class LoginViewModel : ViewModel(){
    private val _uiStateLogin = MutableStateFlow<LoginUiModel>( LoginUiModel())
    val uiStateLogin: StateFlow<LoginUiModel> = _uiStateLogin

    init {
        //Log.d("MochiApp", "${auth.currentUser?.uid}")
        //onNameChanged(EMAIL)
        //onPasswordChanged(PASS)
    }

    fun onNameChanged(email: String) {
        _uiStateLogin.value = _uiStateLogin.value.copy(email = email)
        _uiStateLogin.value = _uiStateLogin.value.copy(isValidEmail = isValidEmail(email))

    }

    fun onPasswordChanged(password: String) {
        _uiStateLogin.value = _uiStateLogin.value.copy(password = password)
        _uiStateLogin.value = _uiStateLogin.value.copy(isValidPassword = isValidPassword(password))
    }

    private fun isValidPassword(password: String): Boolean  = password.length > 0
    private fun isValidEmail(password: String): Boolean  = password.length > 0

     fun onLoginSelected() {
         Timer().schedule(timerTask {_uiStateLogin.value = _uiStateLogin.value.copy(isLoading = true)}, 3000)
    }


    fun detectFingerprint(fingerPrint: String){
        if (fingerPrint.isNotEmpty())
            _uiStateLogin.value = _uiStateLogin.value.copy(isDetected = true)
    }

    private val _LoginUiState = MutableStateFlow<LoginUiState>(
        LoginUiState.LoginScreen()
    )
    val loginUiState: StateFlow<LoginUiState> = _LoginUiState

    fun CrearCuentaScreen(){
        _LoginUiState.value = LoginUiState.CrearCuentaScreen()
    }

    fun LoginScreen(){
        _LoginUiState.value = LoginUiState.LoginScreen()
    }

    //FIREBASE LOGIN
    fun signInWithEmailAndPassword(home: ()-> Unit)
    = viewModelScope.launch{
        try {
            val state = _uiStateLogin.value

            auth.signInWithEmailAndPassword(state.email,state.password)
                .addOnCompleteListener{ task->
                    if(task.isSuccessful){
                        Log.d("MochiApp", "signInWithEmailAndPassword logueado correctamente!!!")
                        home()
                    }
                    else{
                        Log.d("MochiApp", "signInWithEmailAndPassword ${task.result}")
                    }
                }
        }
        catch(ex:Exception){
            Log.d("MochiApp", "signInWithEmailAndPassword ${ex.message}")
        }
    }

    fun createUserWithEmailAndPassword(home: () -> Unit){
        try{
            val state = uiStateLogin.value

            if (!state._loading){
                _uiStateLogin.value = _uiStateLogin.value.copy(_loading = true)
                auth.createUserWithEmailAndPassword(state.email,state.password)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful){
                            val username = auth.currentUser?.email?.split("@")?.get(0)
                            if (username != null) {
                                createUser(username)
                            }
                            home()
                        }
                        else{
                            Log.d("MochiApp", "createUserWithEmailAndPassword ${task.result}")
                        }
                        _uiStateLogin.value = _uiStateLogin.value.copy(_loading = false)
                    }
            }
        }catch(ex:Exception){
            Log.d("MochiApp", "createUserWithEmailAndPassword ${ex.message}")
        }
    }
}

data class LoginUiModel(
    val email: String = "",
    val isValidEmail: Boolean = false,
    val password: String = "",
    val isValidPassword: Boolean = false,
    val fingerPrint: String = "",
    val isLoading: Boolean = false,
    val isDetected: Boolean = false,
    //val auth: FirebaseAuth = Firebase.auth,
    val _loading: Boolean = false,
    val isLoggedIn: Boolean = false
)

sealed class LoginUiState{
    class LoginScreen(): LoginUiState()
    class CrearCuentaScreen(): LoginUiState()
}