package com.app.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Camera
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.app.camera.ui.theme.CameraTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraTheme {
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
    var display by remember { mutableStateOf("") }
    var uri by remember { mutableStateOf(Uri.EMPTY) }
    var photo by remember { mutableStateOf(true) }
    val context = LocalContext.current

    val photoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        Log.d("URI",uri.toString())
        display = if(it) "PHOTO" else ""
    }

    val videoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CaptureVideo()) {
        Log.d("URI",uri.toString())
        display = if(it) "VIDEO" else ""
    }

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

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
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
            }
        )
    }
}

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

fun Context.createFile(photo: Boolean): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val suffix = if(photo) ".jpg" else ".mp4"
    val imageFileName = "FILE_" + timeStamp + "_"
    return File.createTempFile(
        imageFileName,
        suffix,
        externalCacheDir
    )
}