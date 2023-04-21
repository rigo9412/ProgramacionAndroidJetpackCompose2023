package com.example.simondiceviewmodel.screeens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScreenTop10(top10ViewModel: Top10ViewModel){
    val state = top10ViewModel.state
    LazyColumn(modifier= Modifier.fillMaxWidth()){
        items(state.records){
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = it.record.toString())
                Text(text = it.name)
                Divider()
            }
        }
    }
}