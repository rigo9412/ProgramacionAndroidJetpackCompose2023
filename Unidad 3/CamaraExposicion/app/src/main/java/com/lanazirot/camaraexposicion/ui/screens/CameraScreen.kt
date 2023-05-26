package com.lanazirot.camaraexposicion.ui.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView


@Composable
fun ImagePreview(uri: Uri) {
    AsyncImage(model = uri, contentDescription = "Content")
}

@Composable
fun VideoPreview(uri: Uri) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(LocalContext.current).build()
        .also { exoPlayer ->
            val mediaItem = MediaItem.Builder().setUri(uri).build()
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
        }

    DisposableEffect(
        AndroidView(factory = {
            StyledPlayerView(context).apply {
                player = exoPlayer
            }
        })
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}

@Composable
fun ButtonRow(
    onCameraClick: () -> Unit,
    onVideoClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Button(onClick = onCameraClick) {
            Text(text = "Foto")
        }
        Button(onClick = onVideoClick) {
            Text(text = "Foto")
        }
    }

}

@Composable
fun CameraSreen() {
    val context = LocalContext.current
    var uri by remember { mutableStateOf(Uri.EMPTY) }
    var display by remember { mutableStateOf("") }


    var photo by remember { mutableStateOf(true) }

    val photoLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) {
            display = if (it) "Fotografia" else ""
        }

    val videoLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.CaptureVideo()) {
            display = if (it) "Video" else ""
        }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
}