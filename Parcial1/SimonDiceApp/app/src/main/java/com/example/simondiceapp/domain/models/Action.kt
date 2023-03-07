package com.example.simondiceapp.domain.models

enum class Action(val value:Int) {
    NO_ACTION(-1) ,
    PRESS_GREEN_BUTTON(0) ,
    PRESS_RED_BUTTON(1),
    PRESS_BLUE_BUTTON(2),
    PRESS_YELLOW_BUTTON(3)
}