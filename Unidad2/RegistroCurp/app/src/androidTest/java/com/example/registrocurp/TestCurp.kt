package com.example.registrocurp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import org.junit.Test
import org.junit.Rule
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.registrocurp.curp.components.RadioButtonGroupSex
import com.example.registrocurp.curp.ui.form.ui.CurpFormEvent
import com.example.registrocurp.curp.ui.form.ui.Form
import com.example.registrocurp.curp.ui.form.ui.FormViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before


class TestCurp {

    @get:Rule
    val rule = createComposeRule()

    private lateinit var formVM : FormViewModel

    @Before
    fun init(){
        formVM = FormViewModel()
        rule.setContent {
            var navHostController = rememberNavController()
            var data = formVM.uiStateData.collectAsState()
            Form(formVM)
        }
    }

    @Test
    fun customInputTest(){
        rule.onNodeWithTag("Name").assert(hasText(""))
        rule.onNodeWithTag("Middle_name").assert(hasText(""))
        rule.onNodeWithTag("Lastname").assert(hasText(""))
    }

    @Test
    fun verify_that_the_customInputs_work(){
        val name = "Andrea Joana"
        val middlename = "Martinez"
        val lastname = "Limon"

        val nombretxt = rule.onNodeWithTag("Name")
        val pApellidotxt = rule.onNodeWithTag("Middle_name")
        val sApellidotxt = rule.onNodeWithTag("Lastname")

        nombretxt.performTextInput(name)
        pApellidotxt.performTextInput(middlename)
        sApellidotxt.performTextInput(lastname)

        nombretxt.assert(hasText(name.uppercase()))
        pApellidotxt.assert(hasText(middlename.uppercase()))
        sApellidotxt.assert(hasText(lastname.uppercase()))
    }
}