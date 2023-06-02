package com.tec.camara.screen

import android.annotation.SuppressLint
import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
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