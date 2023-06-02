package com.tec.camara

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.tec.camara.ui.theme.CamaraTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CamaraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CameraScreen()
                }
            }
        }
    }
}


@Composable
fun CameraScreen() {
    //Saber si mostrar la foto o el video
    var display by remember { mutableStateOf("")}

    //URI de la foto o video
    var uri by remember { mutableStateOf(Uri.EMPTY)}

    //Variable para saber si se va a lanzar la camara para tomar video o foto
    var photo by remember { mutableStateOf(true)}

    val context = LocalContext.current

    // Lanzador para tomar fotos
    val photoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        Log.d("URI",uri.toString())
        display = if(it) "PHOTO" else ""
    }

    // Lanzador para grabar videos
    val videoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CaptureVideo()) {
        Log.d("URI",uri.toString())
        display = if(it) "VIDEO" else ""
    }

    // Lanzador para solicitar permisos de cámara
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            if(photo){
                photoLauncher.launch(uri)
            }
            else{
                videoLauncher.launch(uri)
            }
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    // Crear la interfaz de usuario con botones y vista previa de imagen o video
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
        if(display == "PHOTO"){
            ImagePreview(uri = uri, modifier = Modifier.weight(1f))
        }
        else if(display == "VIDEO"){
            VideoPreview(uri = uri, modifier = Modifier.weight(1f))
        }

        ButtonRow(
            onTakePhoto = {
                display = ""
                val file = context.createFile(true)
                uri = FileProvider.getUriForFile(
                    Objects.requireNonNull(context),
                    BuildConfig.APPLICATION_ID+".provider",file
                )
                val permissionCheckResult = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    photoLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }

        },
            onTakeVideo = {
                display = ""
                val file = context.createFile(false)
                uri = FileProvider.getUriForFile(
                    Objects.requireNonNull(context),
                    BuildConfig.APPLICATION_ID+".provider",file
                )

                val permissionCheckResult = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                    videoLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }

        })
    }
}

// Fila de botones para tomar fotos y grabar videos
@Composable
fun ButtonRow(onTakePhoto: () -> Unit, onTakeVideo: () -> Unit){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
        Button(onClick = onTakePhoto){
            Text("Tomar foto")
        }
        Button(onClick = onTakeVideo){
            Text("Tomar Video")
        }
    }
}


//Utility or Helper Function
// Función para crear un archivo temporal para almacenar fotos y videos
fun Context.createFile(photo: Boolean): File {
    // Crear un nombre de archivo basado en la hora actual
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val suffix = if(photo) ".jpg" else ".mp4"
    val imageFileName = "FILE_" + timeStamp + "_"
    return File.createTempFile(
        imageFileName, /* prefix */
        suffix, /* suffix */
        externalCacheDir      /* directory */
    )
}

