package com.example.simondice

import androidx.compose.ui.test.junit4.createComposeRule
import com.example.simondice.domain.models.Player
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class SimonDiceTest {

 @get:Rule
 var rule = createComposeRule()
    val playerTest = Player()
    val scoreMapTest = mutableMapOf<String, Int>()
  @get:Rule

 @Test
 fun Testd_when_is_new_user_in_top(){
     scoreMapTest.put("Jordan",90)
     scoreMapTest.put("Soto",80)
     rule.setContent {
         Top10Scores(player = playerTest, scores =scoreMapTest )
         assertEquals(90,scoreMapTest["Jordan"])
     }

 }
}