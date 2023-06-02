package com.almy.mochiapp.screens.CreateTask

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almy.mochiapp.firebase.auth
import com.almy.mochiapp.firebase.currentUser
import com.almy.mochiapp.models.Activity
import com.almy.mochiapp.models.Assigment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import kotlin.random.Random

class AssigmentViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ScreenUiState>(ScreenUiState.Init)
    val uiState: StateFlow<ScreenUiState> = _uiState

    private val _uiStateData = MutableStateFlow<AssigmentData>(AssigmentData())
    val uiStateData: StateFlow<AssigmentData> = _uiStateData


    init {

        //_uiState.value = ScreenUiState.Done
        getCurrentUserName()
        getUserNames()
    }

    fun getCurrentUserName() {
        if(_uiState.value != ScreenUiState.Loading)
        {
            _uiState.value = ScreenUiState.Loading
        }
        var username: String? = null

        viewModelScope.launch {
            var espera = FirebaseFirestore.getInstance().collection("users")
                .get()
                .await()
                .documents
                .firstOrNull { it.getString("id") == auth.currentUser?.uid }
                ?.get("username")
            username = espera.toString()
            Log.d("CurrentUser", "username: $username")

            _uiStateData.value = _uiStateData.value.copy(currentUserName = username!!)

            //_uiState.value = ScreenUiState.Ready
        }
    }

    fun getCurrentUserName(
        memberList: List<String>,
        titleAssigment: String,
        activities: List<Activity>,
    ) {
        _uiState.value = ScreenUiState.Loading

        var username: String? = null
        viewModelScope.launch {
            FirebaseFirestore.getInstance().collection("users")
                .get()
                .addOnSuccessListener { result ->
                    //val userList = result.toObjects(User::class.java)
                    for (item in result) {
                        Log.d("MochiApp", "${item.id} => ${item.data}")
                        if (auth.currentUser?.uid == item.data["id"]) {
                            username = item.getString("username")
                            Log.d("MochiApp", "username: $username")
                            createAssigment(
                                createdBy = username,
                                titleAssigment = titleAssigment,
                                activities = activities,
                                members = memberList,
                                notes = mutableListOf()
                            )
                            break
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("MochiApp", "Error getting documents.", exception)
                }
                .await()

        }
    }

    fun getUserNames() {
        if (_uiState.value != ScreenUiState.Loading) {
            _uiState.value = ScreenUiState.Loading
        }
        var usernames: MutableList<String>? = mutableListOf()
        viewModelScope.launch() {
            FirebaseFirestore.getInstance().collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (item in result) {
                        Log.d("MochiApp", "${item.id} => ${item.data}")
                        usernames?.add(item.getString("username") ?: "")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("MochiApp", "Error getting documents.", exception)
                }
                .await()

            _uiStateData.value = _uiStateData.value.copy(userNames = usernames)

            _uiState.value = ScreenUiState.Done
        }
        //usernames = mutableListOf(espera.await().toString())
        //usernames
    }

    fun createAssigment(
        createdBy: String?,
        titleAssigment: String,
        activities: List<Activity>,
        members: List<String>,
        notes: MutableList<String>
    ) {
        if (_uiState.value != ScreenUiState.Loading) {
            _uiState.value = ScreenUiState.Loading
        }

        val assigment = Assigment(
            id = getAssignmentID(),
            titleAssigment = titleAssigment,
            createdBy = createdBy,
            activities = activities,
            progress = 0,
            members = members,
            notes = notes
        ).toMap()

        viewModelScope.launch {
            //postear tarea
            FirebaseFirestore.getInstance().collection("assigment")
                .add(assigment)
                .addOnSuccessListener {
                    Log.d("MochiApp", "Creado ${it.id})")
                    //_uiState.value = ScreenUiState.Done
                }
                .addOnFailureListener {
                    Log.d("MochiApp", "Ocurrió un problema $it")
                }

            //obtener los id de los miembros de la tarea
            val membersIDDeferred = members.map { member ->
                FirebaseFirestore.getInstance().collection("users")
                    .get()
                    .await()
                    .documents
                    .firstOrNull { it.getString("username") == member }
                    ?.id

            }
            val membersID = membersIDDeferred.filterNotNull()
            Log.d("MochiApp", "lista miembros: ${membersID})")

            //actualizar los usuarios correspondientes con la tarea creada
            membersID.forEach { memberID ->

                //primero guardemos la coleccion tareas que ya tiene el usuario
                val listAssigmentsDeferred =
                    FirebaseFirestore.getInstance().collection("users")
                        .get()
                        .await()
                        .documents
                        .firstOrNull { it.id == memberID }
                        ?.get("assigments")


                //se guarda en una lista la nueva variable
                var assigments = listAssigmentsDeferred
                var listAssigments: MutableList<String> = mutableListOf()
                if (assigments is Collection<*>) {
                    assigments.forEach {
                        listAssigments.add(it.toString())
                    }
                } else {
                    listAssigments.add(assigments.toString())
                }
                listAssigments.add(assigment["id"].toString())

                Log.d("MochiApp", "$memberID => listaTareas: $listAssigments")
                listAssigments.remove("")
                val data = hashMapOf("assigments" to listAssigments)

                //actualizar tareas del usuario
                Firebase.firestore.collection("users").document(memberID)
                    .set(data, SetOptions.merge())
                    .addOnSuccessListener {
                        Log.d("MochiApp", "Actualizado ${it})")
                        //_uiState.value = ScreenUiState.Done
                    }
                    .addOnFailureListener {
                        Log.d("MochiApp", "Ocurrió un problema $it")
                    }
            }
            _uiState.value = ScreenUiState.Done
        }
    }

    fun getAssignmentID(): String {
        var id = "asg" + getRandomChars(3) + "b" + getRandomChars(4) + "mochi"
        return id.toList().shuffled().joinToString("")
    }

    fun getRandomChars(length: Int): String {
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val random = Random(System.currentTimeMillis())
        val result = StringBuilder(length)

        repeat(length) {
            val randomIndex = random.nextInt(chars.size)
            result.append(chars[randomIndex])
        }

        return result.toString()
    }

    fun getUserID(username: String) {
        FirebaseFirestore.getInstance().collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (item in result) {
                    if (item.data["username"] == username) {

                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w("MochiApp", "Error getting documents.", exception)
            }
    }
}

sealed class ScreenUiState {
    object Init : ScreenUiState()
    object Loading : ScreenUiState()
    object Ready : ScreenUiState()
    object Done : ScreenUiState()
}

data class AssigmentData(
    val currentUserName: String = "",
    val userNames: List<String>? = listOf()
)