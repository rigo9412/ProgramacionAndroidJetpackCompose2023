package com.lanazirot.curpgenerator.screens.curp.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.lanazirot.curpgenerator.R

@Composable
fun CustomAlertDialog(
    message: String,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            title = {
                Text(message)
            },
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(stringResource(id = R.string.alert_confirm))
                }
            },
            dismissButton = {}
        )
    }
}