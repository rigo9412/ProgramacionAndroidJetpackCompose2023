package com.example.generadorcurp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.generadorcurp.ui.form.ui.Form
import com.example.generadorcurp.ui.form.ui.FormScreen
import com.example.generadorcurp.ui.form.ui.FormViewModel
import com.example.generadorcurp.ui.global.GlobalProvider
import com.example.generadorcurp.ui.global.GlobalStateScreenViewModel
import com.example.generadorcurp.ui.nav.Navigator
import com.example.generadorcurp.ui.wizard.WizardViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class Pruebas {
    @get:Rule
    var rule = createComposeRule()

    private lateinit var formVM : FormViewModel

    @Before
    fun init(){
        formVM = FormViewModel()
        rule.setContent {
            var navHostController = rememberNavController()
            var data = formVM.uiStateData.collectAsState()
            Form(data.value,navHostController){formVM.onEvent(it)}
        }
    }

    @Test
    fun form_loads_text_input_fields(){
        rule.onNodeWithTag("nombre").assert(hasText(""))
        rule.onNodeWithTag("pApellido").assert(hasText(""))
        rule.onNodeWithTag("sApellido").assert(hasText(""))
    }

    @Test
    fun form_loads_other_fields(){
        rule.onNodeWithTag("fecha").assert(hasText(""))
        rule.onNodeWithTag("sexo").assertExists()
        rule.onNodeWithTag("estados").assert(hasText(""))
    }

    @Test
    fun write_to_text_fields_and_check_if_input_works(){
        val nombre = "Jaime Eduardo"
        val pApellido = "Verdugo"
        val sApellido = "Jimenez"

        val nombretxt = rule.onNodeWithTag("nombre")
        val pApellidotxt = rule.onNodeWithTag("pApellido")
        val sApellidotxt = rule.onNodeWithTag("sApellido")

        nombretxt.performTextInput(nombre)
        nombretxt.assert(hasText(nombre.uppercase()))

        pApellidotxt.performTextInput(pApellido)
        pApellidotxt.assert(hasText(pApellido.uppercase()))

        sApellidotxt.performTextInput(sApellido)
        sApellidotxt.assert(hasText(sApellido.uppercase()))
    }
}