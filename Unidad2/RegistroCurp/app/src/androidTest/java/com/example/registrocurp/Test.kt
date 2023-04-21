package com.example.registrocurp

import androidx.compose.foundation.layout.fillMaxSize
import org.junit.Test
import org.junit.Rule
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.registrocurp.curp.components.RadioButtonGroupSex
import junit.framework.TestCase.assertEquals


class Test {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun radioButtonGroupSexTest() {
        val items = listOf(
            Pair("M","Mujer"),
            Pair("H","Hombre"),
            Pair("X","No Binario"),
        )
        var selectedItem: Pair<String, String>? = null
        composeTestRule.setContent {
            RadioButtonGroupSex(
                modifier = Modifier.fillMaxSize(),
                items = items,
                selection = selectedItem?.first ?: "",
                onItemClick = { selectedItem = it }
            )
        }
        items.forEach { item ->
            composeTestRule.onNodeWithText(text = item.second).performClick()
            assertEquals(item, selectedItem)
        }
    }
}