import android.app.DatePickerDialog
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

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

    dialog.datePicker.maxDate = Calendar.getInstance().timeInMillis

    TextField(
        value = value,
        onValueChange = onValueChange,
        enabled = false,
        readOnly = true,
        modifier = modifier.clickable { dialog.show() },
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default,
        label = { Text(label) },
        textStyle = TextStyle(color = Color.Black)


    )

}