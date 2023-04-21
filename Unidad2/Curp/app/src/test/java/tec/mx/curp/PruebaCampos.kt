package tec.mx.curp

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test
import tec.mx.curp.curp.components.CustomInput

class PruebaCampos {

    @Test
    @Composable
    fun textFieldNotEmptyTest() {
        // Crea un TextField vacío
        val textField = CustomInput(value = "", onValueChange = {})

        // Verifica que el TextField esté vacío
        assertTrue(textField.value.isEmpty())

        // Ingresa un valor en el TextField
        textField.onValueChange("Hola")

        // Verifica que el TextField no esté vacío
        assertFalse(textField.value.isEmpty())
    }
}