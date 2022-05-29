package com.adedom.domain.use_cases.login

import com.adedom.data.models.error.AppErrorCode
import com.adedom.data.models.error.BaseError
import com.adedom.data.models.request.login.LoginRequest
import com.adedom.data.repositories.Resource
import com.adedom.data.repositories.auth.AuthLoginRepository

class LoginUseCase(
    private val authLoginRepository: AuthLoginRepository,
) {

    suspend operator fun invoke(email: String?, password: String?): Resource<Unit> {
        return when {
            email.isNullOrBlank() -> {
                val code = AppErrorCode.EmailIsNullOrBlank.code
                val baseError = BaseError(code = code)
                Resource.Error(baseError)
            }
            email.length < 4 -> {
                val code = AppErrorCode.EmailLessThanFour.code
                val baseError = BaseError(code = code)
                Resource.Error(baseError)
            }
            password.isNullOrBlank() -> {
                val code = AppErrorCode.PasswordIsNullOrBlank.code
                val baseError = BaseError(code = code)
                Resource.Error(baseError)
            }
            password.length < 4 -> {
                val code = AppErrorCode.PasswordLessThanFour.code
                val baseError = BaseError(code = code)
                Resource.Error(baseError)
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