package com.almy.mochiapp.firebase

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.almy.mochiapp.models.Activity
import com.almy.mochiapp.models.Assigment
import com.almy.mochiapp.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await


val auth: FirebaseAuth = Firebase.auth
private lateinit var database: DatabaseReference

const val EMAIL = "claudio@hotmail.com"
const val PASS = "aquiamedianoche"
var currentUser: User? = null


fun getCurrentUserName(): String? = runBlocking {
    var username: String? = null
    var espera = async{
        FirebaseFirestore.getInstance().collection("users")
            .get()
            .await()
            .documents
            .firstOrNull { it.getString("id") == auth.currentUser?.uid }
            ?.get("username")
    }
    username = espera.await().toString()
    Log.d("pruebafuera", "username: $username")
    username
}


fun createUser(username: String)
{
    val userId = auth.currentUser?.uid
    val user = User(
        id = userId.toString(),
        username = username,
        friends = listOf(),
        assigments = listOf<Assigment>()
    ).toMap()

    FirebaseFirestore.getInstance().collection("users")
        .add(user)
        .addOnSuccessListener {
            Log.d("MochiApp", "Creado ${it.id})")
        }
        .addOnFailureListener {
            Log.d("MochiApp", "Ocurrió un problema $it")
        }
}

fun isLoggedIn(): Boolean = runBlocking {
    var login: Boolean = false

    val loginDeferred = async {
        auth.currentUser?.uid
    }
    login = ( loginDeferred.await() != null )
    login
}