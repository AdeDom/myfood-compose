package com.adedom.domain.use_cases.validate

class ValidateEmailUseCase {

    operator fun invoke(email: String?): Boolean {
        return when {
            email.isNullOrBlank() -> false
            email.length < 4 -> false
            else -> true
        }
    }
}