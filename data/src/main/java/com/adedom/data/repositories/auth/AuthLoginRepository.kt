package com.adedom.data.repositories.auth

import com.adedom.data.models.request.login.LoginRequest
import com.adedom.data.models.response.token.TokenResponse

interface AuthLoginRepository {
    suspend fun callLogin(loginRequest: LoginRequest): TokenResponse?
    suspend fun saveToken(accessToken: String, refreshToken: String)
    suspend fun saveAuthRole()
}