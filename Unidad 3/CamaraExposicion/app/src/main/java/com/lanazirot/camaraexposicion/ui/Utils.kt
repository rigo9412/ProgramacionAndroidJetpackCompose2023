package com.lanazirot.camaraexposicion.ui

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

fun Context.createImageFile(isPhoto: Boolean): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName =  timeStamp + "_"
    return File.createTempFile(
        imageFileName,
        if(isPhoto) ".jpg" else ".mp4",
        externalCacheDir
    )
}