package com.jordandiaz19100170.formulario.ui.theme

import android.app.DatePickerDialog
import android.icu.util.LocaleData
import android.os.Build
import android.widget.NumberPicker.OnValueChangeListener
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ofPattern
import java.util.regex.Pattern

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerBirthDate(
    label: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    pattern: String = "yyyy-MM-dd",
    modifier: Modifier,
    focusManager: FocusManager
) {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = if (value.isNotBlank()) LocalDate.parse(value, formatter) else LocalDate.now()
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            onValueChange(LocalDate.of(year, month + 1, dayOfMonth).toString())
        },
        date.year,
        date.monthValue - 1,
        date.dayOfMonth,
    )
    TextField(
        value = value,
        onValueChange = onValueChange,
        enabled =  false,
        readOnly = true,
        modifier = Modifier.clickable { dialog.show() },
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default,
        label = { Text(text = label) },
        textStyle = TextStyle(color = Color.Black)
    )
}
