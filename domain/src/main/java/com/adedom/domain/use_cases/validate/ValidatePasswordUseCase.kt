package com.adedom.domain.use_cases.validate

class ValidatePasswordUseCase {

    operator fun invoke(password: String?): Boolean {
        return when {
            password.isNullOrBlank() -> false
            password.length < 4 -> false
            else -> true
        }
    }
}