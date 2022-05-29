package com.adedom.domain.use_cases.login

import com.adedom.data.models.request.login.LoginRequest
import com.adedom.data.repositories.Resource
import com.adedom.data.repositories.auth.AuthLoginRepository

class LoginUseCase(
    private val authLoginRepository: AuthLoginRepository,
) {

    suspend operator fun invoke(email: String?, password: String?): Resource<Unit> {
        return when {
            email.isNullOrBlank() -> {
                val messageError = "Email is null or blank."
                Resource.Error(messageError)
            }
            email.length < 4 -> {
                val messageError = "Email is incorrect."
                Resource.Error(messageError)
            }
            password.isNullOrBlank() -> {
                val messageError = "Password is null or blank."
                Resource.Error(messageError)
            }
            password.length < 4 -> {
                val messageError = "Password is incorrect."
                Resource.Error(messageError)
            }
            else -> {
                val loginRequest = LoginRequest(email, password)
                authLoginRepository.callLogin(loginRequest)
            }
        }
    }

    fun isValidateEmail(email: String?): Boolean {
        return when {
            email.isNullOrBlank() -> false
            email.length < 4 -> false
            else -> true
        }
    }

    fun isValidatePassword(password: String?): Boolean {
        return when {
            password.isNullOrBlank() -> false
            password.length < 4 -> false
            else -> true
        }
    }
}