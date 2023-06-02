package com.almy.mochiapp.notify

import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.almy.mochiapp.firebase.auth
import com.almy.mochiapp.firebase.getCurrentUserName
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await




fun listenPostAssigments(context: Context) = runBlocking {

    val initialDocumentDeferred = async {
        Firebase.firestore.collection("assigment").get().await()
    }

    var initialDocument = initialDocumentDeferred.await()

    Firebase.firestore.collection("assigment")
        .addSnapshotListener{ snapshot, e ->
            if (e != null) {
                Log.w("SnapshotListener", "Listen failed.", e)
                return@addSnapshotListener
            }

            if(snapshot?.size() != initialDocument.size())
            {
                var newDocument = snapshot?.minus(initialDocument)
                if(newDocument?.size!! > 1)
                {
                    newDocument = newDocument.dropLast(1)
                }
                newDocument?.forEach {
                    if (newDocument != null && it.exists()) {
                        Log.d("SnapshotListener", "Current data: ${it.data}")
                    } else {
                        Log.d("SnapshotListener", "Current data: null")
                    }

                    //crear notificacion
                    val notificationManager: NotificationManagerCompat =
                        provideNotificationManager(context = context)
                    val notificationBuilder: NotificationCompat.Builder =
                        provideNotificationBuilder(
                            context = context,
                            title = it.get("titleAssigment").toString(),
                            content = "${it.get("createdBy")} ha creado una nueva tarea."
                        )

                    //antes de notificar, vamos a checar si el usuario logueado es quien creo la tarea
                    if(getCurrentUserName() == it.get("createdBy"))
                    {
                        //no hacer nada xd
                    }
                    else{
                        //checar si el usuario logueado le corresponde dicha tarea
                        val members = it.get("members") as List<String>
                        if (members.contains(getCurrentUserName()) )
                        {
                            //notificar :D
                            notificationManager.notify(1, notificationBuilder.build())
                        }
                    }
                }
                initialDocument = snapshot
            }
        }
}
