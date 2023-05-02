package com.lanazirot.simonsays

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lanazirot.simonsays.ui.common.components.ui.pad.GameStatus
import com.lanazirot.simonsays.ui.common.components.ui.pad.PadViewModel
import com.lanazirot.simonsays.ui.providers.GlobalProvider
import com.lanazirot.simonsays.ui.providers.LocalGlobalProvider
import com.lanazirot.simonsays.ui.theme.SimonSaysTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SimonSaysGeneralTesting {

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()

        composeTestRule.activity.setContent {
            val padViewModel = composeTestRule.activity.viewModels<PadViewModel>().value
            val navController = rememberNavController()
            val gp = GlobalProvider(padViewModel = padViewModel, nav = navController)
            SimonSaysTheme {
                CompositionLocalProvider(LocalGlobalProvider provides gp) {
                    Surface(
                        Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                    ) {
                        SimonSayGame()
                    }
                }
            }
        }
        val padViewModel = composeTestRule.activity.viewModels<PadViewModel>().value
        assert(padViewModel.pad.value.gameStatus == GameStatus.HOLD)

        composeTestRule.onNodeWithTag("btn_start").performClick()
    }

    @Test
    fun test_A_pad_state_should_be_changed_when_btn_start_is_clicked() {
        val padViewModel = composeTestRule.activity.viewModels<PadViewModel>().value
        assert(padViewModel.pad.value.gameStatus == GameStatus.PAD_YELLING)
    }

    @Test
    fun test_B_pad_state_pad_color_sequence_should_not_be_null(){
        val padViewModel = composeTestRule.activity.viewModels<PadViewModel>().value
        assert(padViewModel.pad.value.pad?.colorSequence != null)
    }

    @Test
    fun test_C_when_click_a_color_game_still_be_playable(){
        val padViewModel = composeTestRule.activity.viewModels<PadViewModel>().value
        val color = padViewModel.pad.value.pad?.colorSequence?.get(0)?.color?.name?.lowercase()
        composeTestRule.onNodeWithTag("btn_$color").performClick()
        assert(padViewModel.pad.value.gameStatus == GameStatus.PAD_YELLING)
    }



}