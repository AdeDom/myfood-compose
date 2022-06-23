package com.adedom.data.repositories.auth

import com.adedom.data.models.request.login.LoginRequest

interface AuthLoginRepository {
    suspend fun callLogin(loginRequest: LoginRequest)
}