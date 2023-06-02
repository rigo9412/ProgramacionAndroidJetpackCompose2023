package com.almy.mochiapp.screens.MainSreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.almy.mochiapp.firebase.auth
import com.almy.mochiapp.firebase.isLoggedIn
import com.almy.mochiapp.models.Activity
import com.almy.mochiapp.models.Assigment
import com.almy.mochiapp.screens.CreateTask.AssigmentScreen
import com.almy.mochiapp.screens.CreateTask.Loading
import com.almy.mochiapp.screens.CreateTask.ScreenUiState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import java.util.*
import kotlin.concurrent.timerTask


class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<ScreenUiState>(ScreenUiState.Init)
    val uiState: StateFlow<ScreenUiState> = _uiState

    private val _uiStateData = MutableStateFlow<AssigmentsData>(AssigmentsData())
    val uiStateData: StateFlow<AssigmentsData> = _uiStateData

    //var currentAssigment: Assigment = Assigment()

    init {
        _uiState.value = ScreenUiState.Loading
        getCurrentUserName()
        getUserAssigments()
        Timer().schedule(timerTask {
            _uiState.value = ScreenUiState.Ready
        }, 5000)
        listenPostAssigments()
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

    fun reloadAssigments() {
        _uiState.value = ScreenUiState.Loading
        _uiStateData.value =
            _uiStateData.value.copy(listAssigment = getUserAssigments() as MutableList<Assigment>)
        Timer().schedule(timerTask {
            _uiState.value = ScreenUiState.Ready
        }, 5000)
    }

    fun getIDAssigments() {
        var listAssigments = mutableListOf("")
        viewModelScope.launch() {
            val listAssigmentsDeferred =
                FirebaseFirestore.getInstance().collection("users")
                    .get()
                    .await()
                    .documents
                    .firstOrNull { it.getString("id") == auth.currentUser?.uid }
                    ?.get("assigments")

            if (listAssigments is Collection<*>) {
                listAssigments = (listAssigmentsDeferred as List<String>).toMutableList()
            } else {
                listAssigments.add(listAssigmentsDeferred.toString())
            }
            _uiStateData.value = _uiStateData.value.copy(listIDAssigments = listAssigments)
        }
        Log.d("getIDAssigments", "ID: $listAssigments")

        //return listAssigments
    }


    fun getUserAssigments() {

        //mandarlo a pantalla de carga
        if (_uiState.value != ScreenUiState.Loading) {
            _uiState.value = ScreenUiState.Loading
        }

        var assigments: List<Assigment> = listOf()

        //obtener IDs de las tareas del usuario
        var listIDAssigments = mutableListOf("")

        //consultar la info de cada tarea
        viewModelScope.launch {

            //obtener IDs de cada tarea
            val listAssigmentsDeferred =
                FirebaseFirestore.getInstance().collection("users")
                    .get()
                    .await()
                    .documents
                    .firstOrNull { it.getString("id") == auth.currentUser?.uid }
                    ?.get("assigments")

            if (listIDAssigments is Collection<*>) {
                listIDAssigments = (listAssigmentsDeferred as List<String>).toMutableList()
            } else {
                listIDAssigments.add(listAssigmentsDeferred.toString())
            }
            _uiStateData.value = _uiStateData.value.copy(listIDAssigments = listIDAssigments)


            //pedir la info de cada tarea con el ID
            val assigmentsDeferred =
                listIDAssigments.mapNotNull { ID ->
                    FirebaseFirestore.getInstance().collection("assigment")
                        .get()
                        .await()
                        .documents
                        .firstOrNull { it.getString("id") == ID }
                        ?.let { document ->

                            val activitiesList = mutableListOf<Activity>()
                            val activitiesData = document.get("activities") as? List<*>
                            activitiesData?.forEach { activityData ->
                                if (activityData is Map<*, *>) {
                                    val activity = Activity(
                                        id = activityData["id"] as? String ?: "",
                                        nameActivity = activityData["nameActivity"] as? String
                                            ?: "",
                                        done = activityData["done"] as? Boolean ?: false
                                    )
                                    activitiesList.add(activity)
                                }
                            }

                            Log.d("activitiesList", "activity: $activitiesList")

                            Assigment(
                                id = document.getString("id"),
                                titleAssigment = document.getString("titleAssigment"),
                                createdBy = document.getString("createdBy"),
                                activities = activitiesList,
                                progress = document.get("progress").toString()?.toInt(),
                                members = document.get("members") as List<String>,
                                notes = document.get("notes") as MutableList<String>
                            )
                        }
                }
            assigments = assigmentsDeferred
            Log.d("getUserAssigmentsCasted", "assigment: $assigments")

            //guardar datos localmente
            _uiStateData.value = _uiStateData.value.copy(listUserAssigments = assigments)

            //cambiar a la pantalla que muestra la info
            Timer().schedule(timerTask {
                _uiState.value = ScreenUiState.Ready
            }, 2500)
        }
        //Log.d("getUserAssigmentsCasted", "assigment: $assigments")
        //return assigments
    }

    fun getAssigment(assigmentID: String) {
        var assigment: Assigment = Assigment()

        //mandarlo a pantalla de carga
        if(_uiState.value != ScreenUiState.Loading)
        {
            _uiState.value = ScreenUiState.Loading
        }

        //ejecutar querys
        viewModelScope.launch() {
            val assigmentDeferred =
                FirebaseFirestore.getInstance().collection("assigment")
                    .get()
                    .await()
                    .documents
                    .firstOrNull { it.getString("id") == assigmentID }
                    ?.let { document ->

                        val activitiesList = mutableListOf<Activity>()
                        val activitiesData = document.get("activities") as? List<*>
                        activitiesData?.forEach { activityData ->
                            if (activityData is Map<*, *>) {
                                val activity = Activity(
                                    id = activityData["id"] as? String ?: "",
                                    nameActivity = activityData["nameActivity"] as? String ?: "",
                                    done = activityData["done"] as? Boolean ?: false
                                )
                                activitiesList.add(activity)
                            }
                        }

                        Log.d("activitiesList", "activity: $activitiesList")

                        Assigment(
                            id = document.getString("id"),
                            titleAssigment = document.getString("titleAssigment"),
                            createdBy = document.getString("createdBy"),
                            activities = activitiesList,
                            progress = document.get("progress").toString()?.toInt(),
                            members = document.get("members") as List<String>,
                            notes = document.get("notes") as MutableList<String>
                        )
                    }
            assigment = assigmentDeferred ?: Assigment()

            //guardar dato localmente
            _uiStateData.value = _uiStateData.value.copy(
                currentAssigment = assigment
            )

            //mandarlo a pantalla
            Timer().schedule(timerTask {
                _uiState.value = ScreenUiState.Ready
            }, 2500)
        }
        //return assigment
    }

    fun deleteAssigment(idAssigment: String) {
        if (_uiState.value != ScreenUiState.Loading) {
            _uiState.value = ScreenUiState.Loading
        }
        viewModelScope.launch() {

            //buscar la tarea
            val assigment = FirebaseFirestore.getInstance().collection("assigment")
                .get()
                .await()
                .documents
                .firstOrNull { it.getString("id") == idAssigment }

            //eliminar tarea
            FirebaseFirestore.getInstance().collection("assigment").document(assigment?.id ?: "")
                .delete()
                .await()

            //eliminar la tarea de la lista que tienen los usuarios

            //de cada miembro
            val members = assigment?.get("members") as List<String>
            //traer su ID
            val membersIDDeferred = members.map { member ->
                FirebaseFirestore.getInstance().collection("users")
                    .get()
                    .await()
                    .documents
                    .firstOrNull { it.getString("username") == member }
                    ?.id
            }
            val membersID = membersIDDeferred.filterNotNull()

            //recorrer los usuarios correspondientes
            membersID.forEach { memberID ->

                //primero guardemos la coleccion tareas que ya tiene el usuario
                val listAssigmentsDeferred =
                    FirebaseFirestore.getInstance().collection("users")
                        .get()
                        .await()
                        .documents
                        .firstOrNull { it.id == memberID }
                        ?.get("assigments")

                //guardamos localmente los id de las tareas
                var assigments = listAssigmentsDeferred
                var listAssigments: MutableList<String> = mutableListOf()
                if (assigments is Collection<*>) {
                    assigments.forEach {
                        listAssigments.add(it.toString())
                    }
                } else {
                    listAssigments.add(assigments.toString())
                }

                //ahora borramos la correspondiente
                listAssigments.remove(idAssigment)

                Log.d("MochiApp", "$memberID => listaTareas: $listAssigments")
                listAssigments.remove("")
                val data = hashMapOf("assigments" to listAssigments)

                //y actualizamos las tareas del usuario con la tarea ya borrada
                Firebase.firestore.collection("users").document(memberID)
                    .set(data, SetOptions.merge())
                    .addOnSuccessListener {
                        Log.d("MochiApp", "Actualizado ${it})")
                        //_uiState.value = ScreenUiState.Done
                    }
                    .addOnFailureListener {
                        Log.d("MochiApp", "Ocurrió un problema $it")
                    }
                    .await()
            }

            getUserAssigments()
        }

        /*_uiStateData.value =
            _uiStateData.value.copy(listAssigment = getUserAssigments() as MutableList<Assigment>)*/
    }

    fun updateAssigment(idAssigment: String) {

        GlobalScope.launch(Dispatchers.IO) {
            //actualicemos el progreso de las tareas...
            val total = _uiStateData.value.currentAssigment.activities?.size
            var realizadas = 0
            _uiStateData.value.currentAssigment.activities?.forEach {
                if (it.done == true) {
                    realizadas++
                }
            }

            _uiStateData.value.currentAssigment.progress = (realizadas * 100) / total!!
            //buscar la tarea
            val assigmentDocID = FirebaseFirestore.getInstance().collection("assigment")
                .get()
                .await()
                .documents
                .firstOrNull { it.getString("id") == idAssigment }
                ?.id

            val dataActivities = _uiStateData.value.currentAssigment.activities?.map {
                hashMapOf(
                    "done" to it.done,
                    "id" to "",
                    "nameActivity" to it.nameActivity
                )
            }

            val dataAssigment = hashMapOf(
                "activities" to dataActivities,
                "notes" to _uiStateData.value.currentAssigment.notes,
                "progress" to _uiStateData.value.currentAssigment.progress,
            )

            Firebase.firestore.collection("assigment").document(assigmentDocID ?: "")
                .set(dataAssigment, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d("MochiApp", "Actualizado ${it})")
                    //_uiState.value = ScreenUiState.Done
                }
                .addOnFailureListener {
                    Log.d("MochiApp", "Ocurrió un problema $it")
                }
                .await()
        }
    }

    fun listenPostAssigments() {
        Firebase.firestore.collection("assigment")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w("SnapshotListener", "Listen failed.", e)
                    return@addSnapshotListener
                }
                _uiState.value = ScreenUiState.Loading
                getUserAssigments()
                Timer().schedule(timerTask {
                    _uiState.value = ScreenUiState.Ready
                }, 2500)
            }
    }

    fun marcarCasillaActividad(index: Int, done: Boolean)
    {
        var assigment = _uiStateData.value.currentAssigment

        assigment.activities?.get(index)?.done = done

        _uiStateData.value = _uiStateData.value.copy(
            currentAssigment = assigment
        )
    }

    fun agregarNota(nota: String){
        var assigment = _uiStateData.value.currentAssigment
        assigment.notes?.add(nota)
        _uiStateData.value = _uiStateData.value.copy(currentAssigment = assigment)
    }

    fun modificarNota(index: Int, nota: String)
    {
        var assigment = _uiStateData.value.currentAssigment
        assigment.notes?.set(index, nota)

        _uiStateData.value = _uiStateData.value.copy(currentAssigment = assigment)
    }
}

data class AssigmentsData(
    var listAssigment: List<Assigment> = listOf(),
    var currentAssigment: Assigment = Assigment(),
    var listIDAssigments: List<String> = listOf(),
    var listUserAssigments: List<Assigment> = listOf(),
    var currentUserName: String = ""
)


