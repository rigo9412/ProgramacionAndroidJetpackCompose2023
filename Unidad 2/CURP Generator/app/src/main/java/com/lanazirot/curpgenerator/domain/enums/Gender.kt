package com.lanazirot.curpgenerator.domain.enums

enum class Gender(val value: String, val code: String) {
    MALE("Hombre", "H"),
    FEMALE("Mujer", "M"),
    NON_BINARY("No binario", "N");

    override fun toString(): String {
        return value
    }
}