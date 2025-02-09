package com.rigo.simondice.ui.top

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings.Global.getString
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import com.rigo.simondice.R
import com.rigo.simondice.domain.models.Player

@Composable
fun TopScreen(modifier: Modifier) {
    val topViewModel = hiltViewModel<TopViewModel>()
    val state = topViewModel.uiState.collectAsState().value



    Box(
        modifier = modifier
    ) {

        when (state) {
            is TopScreenState.Error -> Text(text = state.message, color = Color.White)
            TopScreenState.Loading -> Text(text = "cargando....", color = Color.White)
            is TopScreenState.Ready -> Tops(state.top)

        }
    }
}

@Composable
fun Tops(players: List<Player>) {
    LazyColumn(
        content = {
            itemsIndexed(players) { index, item ->
                ItemCardTop(player = item, index + 1)
            }

        }
    )
}

@Composable
fun ItemCardTop(player: Player, order: Int) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 4.dp),
        backgroundColor = Color.Black,
        border = BorderStroke(
            width = 1.dp, brush = Brush.horizontalGradient(
                listOf(
                    Color.Cyan, Color.Yellow
                )
            )
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "$order. ${player.name}", color = Color.White)
            Text(text = "${player.score} pts", color = Color.White)
        }
    }


}


@Preview
@Composable
fun Item() {
    val player = Player(0, "TEST", 1000000, 1)
    ItemCardTop(player, 1)
}



