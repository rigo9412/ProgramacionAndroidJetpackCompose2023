package com.otop.SimonDice.ui
//
//import android.content.Context
//import android.media.MediaPlayer
//import com.otop.SimonDice.*
//import kotlin.random.Random
//import kotlin.sequences.sequence
//
//class Game {
//
//    var score = 0
//
//
//
//    fun colorTrigger(context: Context,pressed: Int,isSequence:Boolean): Unit {
//        if ( isSequence ){
//            when(pressed){
//                0 -> {
//                    redPressed.value = true
//                    MediaPlayer
//                        .create(context, R.raw.rojo)
//                        .start()
//                }
//                1 -> {
//                    greenPressed.value = true
//                    MediaPlayer
//                        .create(context, R.raw.verde)
//                        .start()
//                }
//                2 -> {
//                    bluePressed.value = true
//                    MediaPlayer
//                        .create(context, R.raw.azul)
//                        .start()
//                }
//                3 -> {
//                    yellowPressed.value = true
//                    MediaPlayer
//                        .create(context, R.raw.amarillo)
//                        .start()
//                }
//            }
//        }
//        else{
//            when(pressed){
//                0 -> {
//                    redPressed.value = true
//                    MediaPlayer
//                        .create(context, R.raw.rojo)
//                        .start()
//                    if (sequence.value[currentTurn.value] != 0) gameOver.value = true
//                }
//                1 -> {
//                    greenPressed.value = true
//                    MediaPlayer
//                        .create(context, R.raw.verde)
//                        .start()
//                    if (sequence.value[currentTurn.value] != 1) gameOver.value = true
//                }
//                2 -> {
//                    bluePressed.value = true
//                    MediaPlayer
//                        .create(context, R.raw.azul)
//                        .start()
//                    if (sequence.value[currentTurn.value] != 2) gameOver.value = true
//                }
//                3 -> {
//                    yellowPressed.value = true
//                    MediaPlayer
//                        .create(context, R.raw.amarillo)
//                        .start()
//                    if (sequence.value[currentTurn.value] != 3) gameOver.value = true
//                }
//            }
//            if (currentTurn.value == sequence.value.size-1) {
//                currentTurn.value = 0
//                roundEnd.value = true
//                return
//            }
//            currentTurn.value++
//        }
//
//
//    }
//
//
//    fun StartGame(): Unit {
//        nextRound()
//    }
//
//    fun nextRound(): Unit {
//        level.value++
//        println(level.value)
//        var auxs = mutableListOf<Int>()
//        for ( i in 0..level.value ){
//            var rand = Random.nextInt(0,4)
//            auxs.add(rand)
//        }
//        sequence.value = auxs
//    }
//}