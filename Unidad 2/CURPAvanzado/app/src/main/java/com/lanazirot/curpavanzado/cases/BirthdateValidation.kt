package com.lanazirot.curpavanzado.cases

class BirthdateValidation {
    fun validateBirthdate(birthdate: String): ValidationResult {
        if (birthdate.isEmpty()) {
            return ValidationResult.Invalid("La fecha de nacimiento no puede estar vacía")
        }
        return ValidationResult.Valid
    }
}