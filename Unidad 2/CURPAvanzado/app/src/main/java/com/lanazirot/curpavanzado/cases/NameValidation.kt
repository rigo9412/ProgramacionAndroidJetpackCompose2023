package com.lanazirot.curpavanzado.cases

class NameValidation {
    fun validateName(name: String): ValidationResult{
        if (name.isEmpty()) {
            return ValidationResult.Invalid("El nombre no puede estar vacío")
        }
        return ValidationResult.Valid
    }

    fun validateLastName(lastName: String): ValidationResult{
        if (lastName.isEmpty()) {
            return ValidationResult.Invalid("El apellido no puede estar vacío")
        }
        return ValidationResult.Valid
    }
}