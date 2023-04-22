package com.morin.curp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import org.junit.Test
import org.junit.Rule
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.morin.curp.curp.components.RadioButtonGroupSex
import com.morin.curp.curp.ui.form.CurpFormEvent
import com.morin.curp.curp.ui.form.Form
import com.morin.curp.curp.ui.form.FormViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.runner.RunWith


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
        val name = "Carlos Daniel"
        val middlename = "Morin"
        val lastname = "Rabago"
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