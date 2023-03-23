package com.lanazirot.curpavanzado.screens.components.step

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lanazirot.curpavanzado.R
import com.lanazirot.curpavanzado.ui.theme.BANNER_GOBIERNO_COLOR
import com.lanazirot.curpavanzado.ui.theme.BANNER_GOBIERNO_COLOR_LIGHT


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StepScreen(
    title: String,
    subtitle: String,
    onBack: () -> Unit = { },
    onNext: () -> Unit = { },
    onSkip: () -> Unit = { },
    isFirst: Boolean = false,
    isLast: Boolean = false,
    content: @Composable () -> Unit,
) {
    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier.padding(bottom = 10.dp),
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BANNER_GOBIERNO_COLOR_LIGHT)
            ) {
                Column(
                    Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = title,
                        Modifier
                            .fillMaxWidth(0.75f)
                            .size(50.dp),
                        color = Color.White,
                        fontSize = 30.sp
                    )
                    Text(
                        text = subtitle,
                        Modifier
                            .fillMaxWidth(0.75f)

                            .size(20.dp),
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }

                Column {
                    TextButton(
                        onClick = if (isFirst) onBack else onSkip,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent,
                            contentColor = Color.White
                        ),
                        border = BorderStroke(0.dp, Color.Transparent),

                        ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(id = R.string.screen_back)
                        )
                    }
                }

            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.weight(1f).fillMaxWidth()
                ) {
                    content()
                }
                Row(
                    modifier = Modifier.weight(0.1f).fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    FloatingActionButton(
                        onClick = { onNext()},
                        backgroundColor = BANNER_GOBIERNO_COLOR,
                        contentColor = Color.White,
                    ) {
                        Icon(
                            imageVector = if (isLast) Icons.Default.Done else Icons.Default.ArrowForward,
                            contentDescription = stringResource(id = R.string.screen_forward)
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun StepScreenPreview() {
    StepScreen(
        title = "Title",
        subtitle = "Subtitle",
        isFirst = true,
        content = {
            Text(text = "Content")
        }
    )
}