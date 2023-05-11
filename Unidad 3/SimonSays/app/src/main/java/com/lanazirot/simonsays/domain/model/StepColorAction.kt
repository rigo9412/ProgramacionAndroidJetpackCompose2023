package com.lanazirot.simonsays.domain.model

import com.lanazirot.simonsays.domain.model.enums.SimonColorPad

data class StepColorAction(override val order: Int, val color: SimonColorPad) : Action(order) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StepColorAction) return false
        return order == other.order && color == other.color
    }
}