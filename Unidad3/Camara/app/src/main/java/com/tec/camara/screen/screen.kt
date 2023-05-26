package com.tec.camara.screen

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.tec.camara.BuildConfig
import com.tec.camara.ImagePreview
import com.tec.camara.VideoPreview
import java.util.*

@Composable
fun CameraScreen() {
    var display by remember { mutableStateOf("")}
    var uri by remember { mutableStateOf(Uri.EMPTY)}
    var photo by remember { mutableStateOf(true)}

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