package com.rigo.simondice.domain.models

enum class Actions(val value: Int) {
    PRESS_GREEN_BUTTON(0) ,
    PRESS_RED_BUTTON(1),
    PRESS_BLUE_BUTTON(2),
    PRESS_YELLOW_BUTTON(3);

    open fun fromInt(x: Int): Actions {
        when (x) {
            0 -> return PRESS_GREEN_BUTTON
            1 -> return PRESS_RED_BUTTON
            2 -> return PRESS_BLUE_BUTTON
            3 -> return PRESS_YELLOW_BUTTON
        }
        return PRESS_GREEN_BUTTON
    }
}