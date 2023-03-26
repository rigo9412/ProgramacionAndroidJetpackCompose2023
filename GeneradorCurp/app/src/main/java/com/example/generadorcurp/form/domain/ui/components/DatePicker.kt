package com.example.generadorcurp.form.ui.components

import android.app.DatePickerDialog
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

@Composable
fun DatePicker(label :String,
                    pattern: String = "yyyy-MM-dd",
                    value : String,
                    onValueChange: (String) -> Unit,
                    modifier : Modifier,
                    focusManager : FocusManager
){

    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = if (value.isNotBlank()) LocalDate.parse(value,formatter) else LocalDate.now()
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            onValueChange(selectedDate.toString())
        },
        date.year,
        date.monthValue - 1,
        date.dayOfMonth
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        enabled = false,
        readOnly = true,
        modifier = Modifier
            .clickable { dialog.show() }
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default,
        label = { Text(label) },
        textStyle = TextStyle(color = Color.Black, fontSize = 17.sp)
    )

}