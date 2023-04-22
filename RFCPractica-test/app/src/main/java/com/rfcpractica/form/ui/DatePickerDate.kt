package com.rfcpractica.form.ui

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerDate(
    label: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    pattern: String = "yyyy-MM-dd",
    modifier: Modifier,
    focusManager: FocusManager
){
    val formatter= DateTimeFormatter.ofPattern(pattern)
    val date = if(value.isNotBlank()) LocalDate.parse(value,formatter) else LocalDate.now()
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _,year,month,dayOfMonth ->
            onValueChange(LocalDate.of(year,month+1, dayOfMonth).toString())
        },
        date.year,
        date.monthValue -1,
        date.dayOfMonth,
    )
    dialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
    TextField(
        value = value,
        onValueChange = onValueChange,
        enabled = false,
        readOnly = true,
        modifier = Modifier.clickable { dialog.show() }.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default,
        label = { Text(label) },
        textStyle = TextStyle(color = Color.Black, fontSize = 17.sp)
    )
}